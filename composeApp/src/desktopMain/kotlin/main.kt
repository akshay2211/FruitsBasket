import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        state = rememberWindowState(width = 440.dp, height = 930.dp),
        onCloseRequest = ::exitApplication,
        title = "FruitsBasket",
        resizable = false
    ) {
        App()
    }
}