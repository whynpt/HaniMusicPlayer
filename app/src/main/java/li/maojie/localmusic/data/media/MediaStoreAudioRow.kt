package li.maojie.localmusic.data.media

data class MediaStoreAudioRow(
    val id: Long,
    val title: String,
    val artist: String?,
    val album: String?,
    val albumId: Long?,
    val durationMs: Long,
    val sizeBytes: Long,
    val dateModifiedSec: Long,
    val trackNumber: Int?,
    val discNumber: Int?,
    val isMusic: Boolean
)
