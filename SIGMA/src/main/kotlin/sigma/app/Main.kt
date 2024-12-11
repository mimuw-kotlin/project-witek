package sigma.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import sigma.ui.screens.StartScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Navigator(StartScreen())
    }
}
