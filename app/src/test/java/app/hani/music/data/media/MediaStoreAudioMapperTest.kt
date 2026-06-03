package app.hani.music.data.media

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MediaStoreAudioMapperTest {
    @Test
    fun mapValidMusicRowToSong() {
        val row = MediaStoreAudioRow(
            id = 42L,
            title = "Golden Thread",
            artist = "Aster Lane",
            album = "Golden Hour",
            albumId = 7L,
            durationMs = 241_000L,
            sizeBytes = 9_000_000L,
            dateModifiedSec = 1_780_000_000L,
            trackNumber = 3,
            discNumber = null,
            isMusic = true
        )

        val song = MediaStoreAudioMapper.map(row)

        requireNotNull(song)
        assertEquals(42L, song.mediaId)
        assertEquals("content://media/external/audio/media/42", song.uri)
        assertEquals("Golden Thread", song.title)
        assertEquals("Aster Lane", song.artist)
        assertEquals("Golden Hour", song.album)
        assertEquals(7L, song.albumId)
        assertEquals(241_000L, song.durationMs)
        assertEquals(9_000_000L, song.sizeBytes)
        assertEquals(1_780_000_000L, song.dateModifiedSec)
        assertEquals(3, song.trackNumber)
        assertNull(song.discNumber)
        assertEquals("content://media/external/audio/albumart/7", song.artworkUri)
    }

    @Test
    fun mapRejectsNonMusicAudio() {
        val song = MediaStoreAudioMapper.map(validRow(isMusic = false))

        assertNull(song)
    }

    @Test
    fun mapRejectsShortAudio() {
        val song = MediaStoreAudioMapper.map(validRow(durationMs = 20_000L))

        assertNull(song)
    }

    @Test
    fun mapRejectsBlankTitle() {
        val song = MediaStoreAudioMapper.map(validRow(title = "   "))

        assertNull(song)
    }

    private fun validRow(
        title: String = "Golden Thread",
        durationMs: Long = 241_000L,
        isMusic: Boolean = true
    ): MediaStoreAudioRow {
        return MediaStoreAudioRow(
            id = 42L,
            title = title,
            artist = "Aster Lane",
            album = "Golden Hour",
            albumId = 7L,
            durationMs = durationMs,
            sizeBytes = 9_000_000L,
            dateModifiedSec = 1_780_000_000L,
            trackNumber = 3,
            discNumber = null,
            isMusic = isMusic
        )
    }
}
