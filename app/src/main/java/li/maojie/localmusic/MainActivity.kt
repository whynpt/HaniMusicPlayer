package li.maojie.localmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import li.maojie.localmusic.ui.LocalMusicApp
import li.maojie.localmusic.ui.theme.LocalMusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LocalMusicTheme {
                LocalMusicApp()
            }
        }
    }
}
