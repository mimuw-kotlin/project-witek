package sigma.app

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import sigma.businessLogic.impl.managers.ResolutionsManager
import sigma.dataAccess.impl.data.Resolution
import sigma.dataAccess.impl.data.Timeline
import sigma.dataAccess.impl.loggers.ConsoleLogger
import sigma.dataAccess.impl.managers.ConfigurationManager
import sigma.dataAccess.impl.parsers.CsvParser
import java.time.LocalDate
import sigma.ui.views.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
