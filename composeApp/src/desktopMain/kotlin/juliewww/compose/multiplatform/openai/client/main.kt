package juliewww.compose.multiplatform.openai.client

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMultiplatformOpenAIclient",
    ) {
        App()
    }
}