package ludugz.pomodoro.ui.pages.habit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ludugz.pomodoro.ui.components.DotGraph
import ludugz.pomodoro.ui.components.RoundedIcon
import ludugz.pomodoro.ui.theme.PomoTypography


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitPage(navController: NavController) {
    Column(modifier = Modifier) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            onClick = {},
            shape = RoundedCornerShape(size = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Meditation",
                    style = PomoTypography.bodyLarge,
                )
                RoundedIcon(imageVector = Icons.Default.Check)
            }
            DotGraph(modifier = Modifier.padding(8.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(widthDp = 360, heightDp = 360, showBackground = true)
@Composable
fun PreviewHabitPage() {
    HabitPage(navController = rememberNavController())
}