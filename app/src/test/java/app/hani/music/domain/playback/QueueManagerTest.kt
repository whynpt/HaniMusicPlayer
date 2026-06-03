package app.hani.music.domain.playback

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QueueManagerTest {
    @Test
    fun createQueueFromListKeepsSourceItemsAndCurrentIndex() {
        val songs = listOf(
            QueueSong(mediaId = 1L, uri = "content://media/external/audio/media/1", title = "Intro"),
            QueueSong(mediaId = 2L, uri = "content://media/external/audio/media/2", title = "Golden Thread"),
            QueueSong(mediaId = 3L, uri = "content://media/external/audio/media/3", title = "Outro")
        )

        val queue = QueueManager.createQueue(
            source = QueueSource.Album,
            songs = songs,
            startMediaId = 2L
        )

        assertEquals(QueueSource.Album, queue.source)
        assertEquals(3, queue.items.size)
        assertEquals(1, queue.currentIndex)
        assertEquals(2L, queue.currentItem.mediaId)
        assertEquals(PlayMode.Sequential, queue.playMode)
        assertTrue(queue.queueId.startsWith("album-"))
    }
}
