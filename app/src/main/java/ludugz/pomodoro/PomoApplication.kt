package ludugz.pomodoro

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 14 July, 2023
 * Email: ludugz@gmail.com
 */
@HiltAndroidApp
class PomoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}