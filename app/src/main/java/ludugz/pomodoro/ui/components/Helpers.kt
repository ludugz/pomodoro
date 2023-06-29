package ludugz.pomodoro.ui.components


/**
 * Created by Tan N. Truong, on 29 June, 2023
 * Email: ludugz@gmail.com
 */

const val POMODORO_DURATION = 25 * 60 * 1000L

fun Long.timeInMinutes(): Long {
    return ((this / (1000 * 60)) % 60)
}

fun Long.timeInSeconds(): Long {
    return (this / 1000) % 60
}

fun Long.formattedTime() =
    String.format("%02d:%02d", this.timeInMinutes(), this.timeInSeconds())
