package ludugz.pomodoro.ui.helpers

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.io.File


/**
 * Created by Tan N. Truong, on 29 June, 2023
 * Email: ludugz@gmail.com
 */

fun Long.timeInMinutes(): Long {
    return ((this / (60)) % 60)
}

fun Long.timeInSeconds(): Long {
    return (this) % 60
}

fun Long.formattedTime() =
    String.format("%02d:%02d", this.timeInMinutes(), this.timeInSeconds())

fun Int.pixelsToDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}

fun readJsonFile(filePath: String): String {
    // Reading the file content into a String
    return File(filePath).readText(Charsets.UTF_8)
}

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}