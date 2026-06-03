package app.hani.music.domain.playback

object QueueManager {
    fun createQueue(
        source: QueueSource,
        songs: List<QueueSong>,
        startMediaId: Long,
        playMode: PlayMode = PlayMode.Sequential
    ): PlayQueue {
        require(songs.isNotEmpty()) { "Queue must contain at least one song." }

        val currentIndex = songs.indexOfFirst { it.mediaId == startMediaId }
            .takeIf { it >= 0 }
            ?: error("Start song must exist in queue.")

        return PlayQueue(
            queueId = "${source.name.lowercase()}-${System.currentTimeMillis()}",
            source = source,
            items = songs.map { song ->
                QueueItem(
                    mediaId = song.mediaId,
                    uri = song.uri,
                    title = song.title
                )
            },
            currentIndex = currentIndex,
            playMode = playMode
        )
    }
}
