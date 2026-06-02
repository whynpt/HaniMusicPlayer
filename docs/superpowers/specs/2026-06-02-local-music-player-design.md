# Local Music Player Design

## Goal

Build a personal Android local music player that feels like a real music app rather than a file browser. The first version should be stable, modern, and extensible for gradual feature additions.

## Platform

- Language: Kotlin
- UI: Jetpack Compose + Material 3
- Playback: Media3 ExoPlayer + MediaSessionService
- Local library source: MediaStore
- Cache: Room
- Settings: DataStore
- SDK line: `compileSdk 36`, `targetSdk 36`, `minSdk 33`

## Product Scope

First version includes:

- Automatic local music indexing through MediaStore
- Library home screen
- Songs, albums, artists, recent playback, and favorites
- Global search entry
- Full player screen
- Mini player
- Current queue screen
- Background playback
- Notification media controls
- Room cache for library snapshot, favorites, and play history

First version excludes:

- Folder-first browsing
- Lyrics
- Equalizer
- Online metadata matching
- Complex playlist editing
- Drag-and-drop queue ordering

## UI Direction

Use the "Warm Native" direction:

- Bright, clean, album-first UI
- Warm white background without turning into a beige-heavy theme
- Strong visual role for album artwork
- Music-library organization, not path or file management organization
- Material 3 controls with custom spacing, hierarchy, and visual treatment

Primary screens:

- `LibraryHomeScreen`: large Library title, search and settings actions, quick entries for songs/albums/artists/favorites, album rail, recently played, recently added, global mini player.
- `SongsScreen`: music list with small artwork, title, artist/album metadata, sort/search controls, and overflow actions.
- `AlbumsScreen`: album artwork grid with title and artist.
- `AlbumDetailScreen`: large artwork, album metadata, play all, shuffle, and track list.
- `ArtistsScreen`: artist list with generated avatar or representative artwork.
- `ArtistDetailScreen`: artist header, album section, song section.
- `PlayerScreen`: large artwork, soft background color derived from artwork or stable fallback, title, artist, progress, playback controls, favorite, queue, and play mode.
- `QueueScreen`: current queue, current item highlight, jump-to-song, remove item.
- `SettingsScreen`: rescan, sort preferences, theme mode, app info.

Missing artwork should use a designed fallback, such as generated color treatment plus album or artist initials. The app should not show generic engineering placeholder icons as the default visual.

## Architecture

Separate dependency direction from data flow.

Dependency direction:

```text
Compose UI
  -> ViewModel / UiState
  -> Domain models and interfaces
  -> Data and playback implementations
```

Runtime state flows back upward through `Flow`:

```text
Room / MediaStore / Media3
  -> Repository / PlaybackController
  -> ViewModel
  -> Compose UI
```

Modules:

- `ui`: Compose screens, components, navigation, theme.
- `presentation`: ViewModels, UI state, UI actions.
- `domain`: stable app models and repository/controller interfaces.
- `data`: MediaStore scanner, Room database, repository implementation.
- `playback`: MediaSessionService, ExoPlayer owner, MediaController adapter, queue manager.

The UI must not directly own or manipulate ExoPlayer. The player instance belongs to `MediaSessionService`. UI talks to playback through a controller abstraction.

## Data Model

Do not use one model for every layer. Keep separate models:

- `MediaStoreAudio`: raw scanner result close to Android API fields.
- `SongEntity`: Room cache representation.
- `Song`: domain model used by UI and playback.

Core domain fields:

```kotlin
data class Song(
    val mediaId: Long,
    val uri: Uri,
    val title: String,
    val artist: String,
    val album: String,
    val albumId: Long?,
    val durationMs: Long,
    val sizeBytes: Long,
    val dateModifiedSec: Long,
    val trackNumber: Int?,
    val discNumber: Int?,
    val artworkUri: Uri?,
    val isFavorite: Boolean,
)
```

Room tables:

- `songs`: cached MediaStore song snapshot.
- `favorites`: user favorites keyed by media id.
- `play_history`: playback history and last known position.

Albums and artists should be derived from `songs` in the first version rather than maintained as separate canonical tables. This keeps the first version resilient to inconsistent device metadata.

## MediaStore Sync

Startup flow:

```text
Check READ_MEDIA_AUDIO
  -> observe Room cache
  -> render cached library immediately
  -> refresh MediaStore in background
  -> diff scanner result with Room
  -> update Room
  -> UI refreshes through Flow
```

Diff rules:

- Same song: same `mediaId`
- Changed song: `dateModifiedSec`, `sizeBytes`, or `durationMs` changed
- New song: in MediaStore but not Room
- Missing song: in Room but absent from current MediaStore scan

Missing songs should be marked unavailable instead of hard-deleted immediately. User data such as favorites and play history must be preserved.

## Playback

Playback is a long-lived runtime concern, not ordinary data access.

- `MediaSessionService` owns ExoPlayer.
- Activity and Compose UI only render state and send actions.
- `MediaController` connects app UI to the service.
- System notification controls, Bluetooth/headset controls, and background playback go through MediaSession.

Queue should be a stable snapshot created by the play action:

```kotlin
data class PlayQueue(
    val queueId: String,
    val source: QueueSource,
    val items: List<QueueItem>,
    val currentIndex: Int,
    val playMode: PlayMode,
)

enum class QueueSource {
    Songs,
    Album,
    Artist,
    Favorites,
    Recent,
    Search,
    Manual
}

enum class PlayMode {
    Sequential,
    RepeatAll,
    RepeatOne,
    Shuffle
}
```

A queue must not be a live reference to the currently visible list. Sorting, searching, or navigating away should not mutate the active queue unexpectedly.

## Permissions

Required Android permissions:

- `READ_MEDIA_AUDIO`: local audio library access
- `POST_NOTIFICATIONS`: playback notification controls
- `FOREGROUND_SERVICE_MEDIA_PLAYBACK`: stable background playback service

No-permission state should be an explicit permission screen, not an empty library that looks like a scanning failure.

## Modern Android Behavior

The app targets Android 16 behavior through `targetSdk 36`.

- Handle edge-to-edge from the first version.
- Use modern Compose Navigation back handling.
- Keep scanning work out of the playback foreground service.
- Treat MediaStore as the source of truth for indexed local audio.
- Play `content://` URIs rather than relying on raw filesystem paths.

## Testing Strategy

Unit tests:

- MediaStore result mapping
- Room sync diff
- QueueManager for sequential, repeat all, repeat one, and shuffle
- Playback state mapping from Media3 state to app UI state

Integration tests:

- Room DAO behavior
- Repository refresh behavior with fake scanner input

Manual tests:

- First launch with permission granted
- First launch with permission denied
- Add local music, refresh, and verify it appears
- Delete local music and verify unavailable handling
- Background playback
- Notification media controls
- Bluetooth/headset media controls
- Activity recreation during playback
- Edge-to-edge layout on Android 16 target behavior
