package sigma.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sigma.businessLogic.impl.managers.StatisticsManager
import java.time.YearMonth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.lerp

fun lerp(start: Color, end: Color, fraction: Float): Color {
    val startRed = start.red
    val startGreen = start.green
    val startBlue = start.blue
    val startAlpha = start.alpha

    val endRed = end.red
    val endGreen = end.green
    val endBlue = end.blue
    val endAlpha = end.alpha

    val red = startRed + (endRed - startRed) * fraction
    val green = startGreen + (endGreen - startGreen) * fraction
    val blue = startBlue + (endBlue - startBlue) * fraction
    val alpha = startAlpha + (endAlpha - startAlpha) * fraction

    return Color(red, green, blue, alpha)
}

@Composable
fun CalendarScreen() {
    val currentMonth = YearMonth.now()
    val daysInMonth = currentMonth.lengthOfMonth()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Month header
        Text(currentMonth.toString(), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Calendar grid
        for (week in 1..6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (day in 1..7) {
                    val dayOfMonth = (week - 1) * 7 + day
                    if (dayOfMonth <= daysInMonth) {
                        val color = when (val result = StatisticsManager.getResult(dayOfMonth)) { // Placeholder
                            in 0.0..1.0 -> lerp(Color.Red, Color.Green, result.toFloat())
                            -1.0 -> Color.Gray
                            else -> Color.White
                        }
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color, CircleShape)
                                .clickable { /* Navigate to day's details */ }
                        )
                    } else {
                        Spacer(modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}



