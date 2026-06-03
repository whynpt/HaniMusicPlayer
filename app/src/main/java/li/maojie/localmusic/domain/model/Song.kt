package li.maojie.localmusic.domain.model

data class Song(
    val mediaId: Long,
    val uri: String,
    val title: String,
    val artist: String,
    val album: String,
    val albumId: Long?,
    val durationMs: Long,
    val sizeBytes: Long,
    val dateModifiedSec: Long,
    val trackNumber: Int?,
    val discNumber: Int?,
    val artworkUri: String?,
    val isFavorite: Boolean = false
)
