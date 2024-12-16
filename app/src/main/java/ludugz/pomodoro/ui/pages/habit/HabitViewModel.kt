package ludugz.pomodoro.ui.pages.habit

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


/**
 * Created by Tan N. Truong, on 16 December, 2024
 * Email: ludugz@gmail.com
 */
class HabitViewModel : ViewModel() {

    private val titleSet = mutableSetOf<String>()

    val habitItemList = mutableStateListOf<HabitItemInfo>()

    init {
        addHabitItem(HabitItemInfo(emptyList(), "Default Session"))
        addHabitItem(HabitItemInfo(emptyList(), "Default Session"))
    }

    private fun addHabitItem(item: HabitItemInfo) {
        if (titleSet.add(item.title)) {
            habitItemList.add(item)
        }
    }
}