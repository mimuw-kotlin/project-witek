package sigma.ui.views

import androidx.compose.runtime.*
import sigma.dataAccess.impl.data.Timeline
import sigma.dataAccess.impl.loggers.ConsoleLogger
import sigma.dataAccess.impl.managers.ConfigurationManager
import sigma.dataAccess.impl.parsers.CsvParser
import java.time.LocalDate

@Composable
fun App() {
    var initialized by remember { mutableStateOf(false) }
    var showFirstUseInfo by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val configuration = ConfigurationManager()
        val resolutionsPath = configuration.getResolutionsPath()
        val timelinePath = configuration.getTimelinePath()
        val logger = ConsoleLogger()
        val parser = CsvParser(logger)
        val filesExist = parser.checkFiles(resolutionsPath, timelinePath)
        if (!filesExist) {
            showFirstUseInfo = true
        } else {
            parser.readResolutions(resolutionsPath)
            parser.readTimeline(timelinePath)
            initialized = true
        }
    }

    if (!initialized) {
        if (showFirstUseInfo) {
            StartupScreen(onStartNewChallenge = {
                val timeline = Timeline(LocalDate.now(), mutableListOf())
                initialized = true
            }, onImportData = {
                //TODO("import files")
                initialized = true
            })
        } else {
            SplashScreen()
        }
    } else {
        HomeScreen()
    }
}
