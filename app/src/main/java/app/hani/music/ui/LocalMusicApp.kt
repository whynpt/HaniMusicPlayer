package app.hani.music.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hani.music.ui.theme.LocalMusicTheme

@Composable
fun LocalMusicApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = { MiniPlayer() }
    ) { innerPadding ->
        LibraryHome(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun LibraryHome(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item { Header() }
        item { QuickEntries() }
        item { AlbumRail() }
        item { SongSection(title = "Recently Played", songs = SampleSongs.recent) }
        item { SongSection(title = "Recently Added", songs = SampleSongs.added) }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Library",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Local music, ready when you are",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        }
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }
    }
}

@Composable
private fun QuickEntries() {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        QuickEntry(label = "Songs")
        QuickEntry(label = "Albums")
        QuickEntry(label = "Artists")
        QuickEntry(label = "Favorites")
    }
}

@Composable
private fun QuickEntry(label: String) {
    AssistChip(
        onClick = {},
        label = { Text(label) }
    )
}

@Composable
private fun AlbumRail() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitle("Albums")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            items(SampleAlbums) { album ->
                AlbumCard(album)
            }
        }
    }
}

@Composable
private fun AlbumCard(album: SampleAlbum) {
    Column(modifier = Modifier.width(132.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(
            modifier = Modifier
                .size(132.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(album.brush),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = album.initials,
                modifier = Modifier.padding(14.dp),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
        Text(
            text = album.title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = album.artist,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun SongSection(title: String, songs: List<SampleSong>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitle(title)
        songs.forEach { song ->
            SongRow(song)
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun SongRow(song: SampleSong) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(song.brush),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = song.initials,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${song.artist} - ${song.album}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Filled.MoreVert, contentDescription = "More")
        }
    }
}

@Composable
private fun MiniPlayer() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(22.dp),
        tonalElevation = 6.dp,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.inverseSurface
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SampleSongs.recent.first().brush),
                contentAlignment = Alignment.Center
            ) {
                Text("GT", color = Color.White, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Golden Thread",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Aster Lane",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.72f)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
            FilledIconButton(onClick = {}, modifier = Modifier.size(42.dp)) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Play")
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Next",
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
}

private data class SampleAlbum(
    val title: String,
    val artist: String,
    val initials: String,
    val brush: Brush
)

private data class SampleSong(
    val title: String,
    val artist: String,
    val album: String,
    val initials: String,
    val brush: Brush
)

private val CoverRed = Brush.linearGradient(listOf(Color(0xFFE75A5A), Color(0xFF7A1D3A)))
private val CoverBlue = Brush.linearGradient(listOf(Color(0xFF4E8BBE), Color(0xFF1F344C)))
private val CoverGreen = Brush.linearGradient(listOf(Color(0xFF7DA879), Color(0xFF2C4A3D)))
private val CoverBlack = Brush.linearGradient(listOf(Color(0xFF3A3A3A), Color(0xFF111111)))

private val SampleAlbums = listOf(
    SampleAlbum("Golden Hour", "Aster Lane", "GH", CoverRed),
    SampleAlbum("Northline", "Noah Vale", "NL", CoverBlue),
    SampleAlbum("Quiet Signals", "Mira Coast", "QS", CoverGreen),
    SampleAlbum("Afterimage", "The Room", "AI", CoverBlack)
)

private object SampleSongs {
    val recent = listOf(
        SampleSong("Golden Thread", "Aster Lane", "Golden Hour", "GT", CoverRed),
        SampleSong("Paper Moon", "Mira Coast", "Quiet Signals", "PM", CoverGreen),
        SampleSong("Low Tide", "Noah Vale", "Northline", "LT", CoverBlue)
    )

    val added = listOf(
        SampleSong("Afterimage", "The Room", "Afterimage", "AI", CoverBlack),
        SampleSong("Soft Electric", "Aster Lane", "Golden Hour", "SE", CoverRed),
        SampleSong("Harbor Lights", "Noah Vale", "Northline", "HL", CoverBlue)
    )
}

@Preview(showBackground = true)
@Composable
private fun LocalMusicAppPreview() {
    LocalMusicTheme {
        LocalMusicApp()
    }
}
