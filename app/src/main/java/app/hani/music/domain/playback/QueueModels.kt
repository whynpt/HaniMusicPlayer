package app.hani.music.domain.playback

data class QueueSong(
    val mediaId: Long,
    val uri: String,
    val title: String
)

data class QueueItem(
    val mediaId: Long,
    val uri: String,
    val title: String
)

data class PlayQueue(
    val queueId: String,
    val source: QueueSource,
    val items: List<QueueItem>,
    val currentIndex: Int,
    val playMode: PlayMode
) {
    val currentItem: QueueItem
        get() = items[currentIndex]
}

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
