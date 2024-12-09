package sigma.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartupScreen(onStartNewChallenge: () -> Unit, onImportData: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to SIGMA!", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onStartNewChallenge) {
            Text("Start New Challenge")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onImportData) {
            Text("Import Data")
        }
    }
}
