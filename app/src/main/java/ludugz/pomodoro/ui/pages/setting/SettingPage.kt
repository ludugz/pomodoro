package ludugz.pomodoro.ui.pages.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.helpers.SettingsLabelTextStyle
import ludugz.pomodoro.ui.theme.BackgroundColors
import ludugz.pomodoro.ui.theme.RockTypography


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

enum class SettingSelectableType {
    Toggle,
    ColorSelection,
    Expandable,
}

@Composable
fun SettingPage(navController: NavController) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
    ) {
        SettingsLabelTextStyle(text = "Display")
        SelectableItem(
            iconResource = R.drawable.dark_mode_icon,
            label = "Dark Mode",
            selectionType = SettingSelectableType.Toggle,
        )
        SelectableItem(
            iconResource = R.drawable.color_icon,
            label = "Background Color",
            selectionType = SettingSelectableType.ColorSelection,
        )
        Spacer(modifier = Modifier.height(16.dp))
        SettingsLabelTextStyle(text = "Feedback")
        SelectableItem(
            iconResource = R.drawable.request_feature_icon,
            label = "Request a feature",
            selectionType = SettingSelectableType.Expandable,
        )
        SelectableItem(
            iconResource = R.drawable.report_issue_icon,
            label = "Report an issue",
            selectionType = SettingSelectableType.Expandable,
        )
    }
}

@Composable
fun SelectableItem(iconResource: Int, label: String, selectionType: SettingSelectableType) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = ""
            )
            Text(
                text = label,
                modifier = Modifier.padding(16.dp),
                style = RockTypography.bodyMedium,
            )
        }

        when (selectionType) {
            SettingSelectableType.Toggle -> {
                Text(
                    text = "OFF",
                    style = RockTypography.bodyMedium,
                )
            }

            SettingSelectableType.ColorSelection -> {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .background(color = Color(BackgroundColors[0]))
                )
            }

            SettingSelectableType.Expandable -> {
                Icon(
                    painter = painterResource(id = R.drawable.right_arrow_icon),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingPage() {
    SettingPage(navController = rememberNavController())
}