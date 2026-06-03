package app.hani.music.data.media

import app.hani.music.domain.model.Song

object MediaStoreAudioMapper {
    private const val MinimumMusicDurationMs = 30_000L
    private const val AudioContentBaseUri = "content://media/external/audio/media"
    private const val AlbumArtworkBaseUri = "content://media/external/audio/albumart"

    fun map(row: MediaStoreAudioRow): Song? {
        val title = row.title.trim()

        if (!row.isMusic) return null
        if (row.durationMs < MinimumMusicDurationMs) return null
        if (title.isBlank()) return null

        return Song(
            mediaId = row.id,
            uri = "$AudioContentBaseUri/${row.id}",
            title = title,
            artist = cleanUnknown(row.artist),
            album = cleanUnknown(row.album),
            albumId = row.albumId,
            durationMs = row.durationMs,
            sizeBytes = row.sizeBytes,
            dateModifiedSec = row.dateModifiedSec,
            trackNumber = row.trackNumber,
            discNumber = row.discNumber,
            artworkUri = row.albumId?.let { "$AlbumArtworkBaseUri/$it" }
        )
    }

    private fun cleanUnknown(value: String?): String {
        val cleaned = value?.trim()
        return if (cleaned.isNullOrBlank() || cleaned == "<unknown>") {
            "Unknown"
        } else {
            cleaned
        }
    }
}
