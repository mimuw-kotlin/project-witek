package sigma.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import sigma.businessLogic.impl.managers.ResolutionsManager

class HomeScreen(private val manager: ResolutionsManager) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Welcome Back!", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            HomePanel("Calendar") { navigator.push(CalendarScreen(manager)) }
            HomePanel("Today") { /* Navigate to Today View */ }
            HomePanel("Statistics") { /* Navigate to Statistics View */ }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun HomePanel(title: String, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = onClick
        ) {
            Text(
                title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.caption
            )
        }
    }
}