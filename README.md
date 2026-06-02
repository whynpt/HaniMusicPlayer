# Local Music Player

Personal Android local music player.

## Direction

- Kotlin + Jetpack Compose + Material 3
- Media3 ExoPlayer + MediaSessionService
- MediaStore automatic local music indexing
- Room cache for library snapshots, favorites, and playback history
- DataStore for lightweight settings
- `compileSdk 36`, `targetSdk 36`, `minSdk 33`

## Product Goal

Build a polished local music app, not a file browser. The first version focuses on a warm native music-library UI with songs, albums, artists, recent playback, favorites, a full player screen, a queue screen, notification controls, and stable background playback.

## Docs

- Design spec: `docs/superpowers/specs/2026-06-02-local-music-player-design.md`
- macOS setup: `docs/development/macos-setup.md`

## Current Development Status

The Android project skeleton has been started. On this Mac, Android SDK 36 is installed, but the global Homebrew `gradle` command currently fails before project evaluation with a native library loading error. Use the checked-in Gradle wrapper or open the project in Android Studio.
