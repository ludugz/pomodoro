package ludugz.pomodoro


/**
 * Created by Tan N. Truong, on 27 May, 2024
 * Email: ludugz@gmail.com
 */
import android.os.Environment
import android.os.StatFs

class LowDiskSpaceDetector(private val thresholdMB: Long) {

    fun isDiskSpaceLow(): Boolean {
        val stat = StatFs(Environment.getExternalStorageDirectory().path)
        val bytesAvailable = stat.blockSizeLong * stat.availableBlocksLong
        val megabytesAvailable = bytesAvailable / (1024 * 1024)

        return megabytesAvailable < thresholdMB
    }
}