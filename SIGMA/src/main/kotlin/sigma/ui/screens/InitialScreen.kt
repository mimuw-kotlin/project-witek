package sigma.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import sigma.businessLogic.impl.managers.ResolutionsManager
import sigma.dataAccess.impl.data.Timeline
import sigma.dataAccess.impl.managers.ConfigurationManager
import sigma.dataAccess.model.loggers.ILogger
import sigma.dataAccess.model.parsers.IParser
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.time.LocalDate

class InitialScreen(
    var manager: ResolutionsManager?,
    val logger: ILogger,
    val parser: IParser,
    var configuration: ConfigurationManager?
) : Screen {
    @Composable
    override fun Content() {
        var importStatus by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to SIGMA!", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                manager = ResolutionsManager(
                    mutableListOf(),
                    Timeline(LocalDate.now(), mutableListOf()),
                    logger,
                    parser,
                    configuration!!
                )
            }) {
                Text("Start New Challenge")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                try {
                    val selectedFile = selectCsvFile()
                    if (selectedFile != null) {
                        parser.readTimeline(selectedFile.path)
                        importStatus = "Successfully imported: ${selectedFile.name}"
                    } else {
                        importStatus = "No file selected"
                    }
                } catch (e: Exception) {
                    importStatus = "Error importing file: ${e.message}"
                }
            }) {
                Text("Import Data")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(importStatus, fontSize = 14.sp)
        }
    }

    private fun selectCsvFile(): File? {
        val fileDialog = FileDialog(Frame(), "Select CSV File", FileDialog.LOAD).apply {
            isVisible = true
        }

        val filePath = fileDialog.directory?.let { dir ->
            fileDialog.file?.let { file ->
                File(dir, file).absolutePath
            }
        }

        return if (filePath != null) File(filePath) else null
    }
}
