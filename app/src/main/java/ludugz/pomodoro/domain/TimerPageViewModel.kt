package ludugz.pomodoro.domain

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * Created by Tan N. Truong, on 13 June, 2024
 * Email: ludugz@gmail.com
 */
@HiltViewModel
class TimerPageViewModel : ViewModel() {
    var counter = MutableStateFlow(0)
        private set

    var displayDialogCount = MutableStateFlow(0)
        private set
}