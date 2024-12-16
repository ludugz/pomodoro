package ludugz.pomodoro.ui.pages.habit

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel


/**
 * Created by Tan N. Truong, on 16 December, 2024
 * Email: ludugz@gmail.com
 */
class HabitViewModel : ViewModel() {

    val habitItemList =
        mutableStateListOf(
            HabitItemInfo(emptyList(), "Default Session"),
            HabitItemInfo(emptyList(), "Default Session"),
        )
}