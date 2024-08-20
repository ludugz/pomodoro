package ludugz.pomodoro.ui

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


/**
 * Created by Tan N. Truong, on 27 May, 2024
 * Email: ludugz@gmail.com
 */
class UserContentsSaver(private val context: Context) {

    fun saveImage(contentUri: Uri): File? {
        return saveContent(contentUri, "images", "IMG_", ".jpg")
    }

    fun saveVideo(contentUri: Uri): File? {
        return saveContent(contentUri, "videos", "VID_", ".mp4")
    }

    private fun saveContent(contentUri: Uri, directoryName: String, filePrefix: String, fileSuffix: String): File? {
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(contentUri)
            val storageDir = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), directoryName)

            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            val file = File.createTempFile(filePrefix, fileSuffix, storageDir)
            val outputStream = FileOutputStream(file)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            return file
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}