package app.hani.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.hani.music.ui.LocalMusicApp
import app.hani.music.ui.theme.LocalMusicTheme

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
