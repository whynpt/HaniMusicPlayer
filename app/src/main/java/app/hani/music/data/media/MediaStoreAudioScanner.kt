package app.hani.music.data.media

import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore
import app.hani.music.domain.model.Song

class MediaStoreAudioScanner(
    private val contentResolver: ContentResolver
) {
    fun scan(): List<Song> {
        val songs = mutableListOf<Song>()

        contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            Projection,
            null,
            null,
            "${MediaStore.Audio.Media.DATE_MODIFIED} DESC"
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                cursor.toAudioRow()?.let(MediaStoreAudioMapper::map)?.let(songs::add)
            }
        }

        return songs
    }

    private fun Cursor.toAudioRow(): MediaStoreAudioRow? {
        val id = getLongOrNull(MediaStore.Audio.Media._ID) ?: return null
        val title = getStringOrNull(MediaStore.Audio.Media.TITLE).orEmpty()

        return MediaStoreAudioRow(
            id = id,
            title = title,
            artist = getStringOrNull(MediaStore.Audio.Media.ARTIST),
            album = getStringOrNull(MediaStore.Audio.Media.ALBUM),
            albumId = getLongOrNull(MediaStore.Audio.Media.ALBUM_ID),
            durationMs = getLongOrNull(MediaStore.Audio.Media.DURATION) ?: 0L,
            sizeBytes = getLongOrNull(MediaStore.Audio.Media.SIZE) ?: 0L,
            dateModifiedSec = getLongOrNull(MediaStore.Audio.Media.DATE_MODIFIED) ?: 0L,
            trackNumber = getIntOrNull(MediaStore.Audio.Media.TRACK),
            discNumber = getIntOrNull(MediaStore.Audio.Media.DISC_NUMBER),
            isMusic = getIntOrNull(MediaStore.Audio.Media.IS_MUSIC) == 1
        )
    }

    private fun Cursor.getStringOrNull(columnName: String): String? {
        val index = getColumnIndex(columnName)
        return if (index >= 0 && !isNull(index)) getString(index) else null
    }

    private fun Cursor.getLongOrNull(columnName: String): Long? {
        val index = getColumnIndex(columnName)
        return if (index >= 0 && !isNull(index)) getLong(index) else null
    }

    private fun Cursor.getIntOrNull(columnName: String): Int? {
        val index = getColumnIndex(columnName)
        return if (index >= 0 && !isNull(index)) getInt(index) else null
    }

    private companion object {
        val Projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_MODIFIED,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.DISC_NUMBER,
            MediaStore.Audio.Media.IS_MUSIC
        )
    }
}
