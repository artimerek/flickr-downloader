package pl.artimerek.flickerdownloader.download.impl

import android.os.AsyncTask
import android.util.Log
import pl.artimerek.flickerdownloader.download.OnDownloadComplete
import pl.artimerek.flickerdownloader.enum.JsonStatus
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

private const val TAG = "GetRawData"

class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {

    private var downloadStatus = JsonStatus.IDLE

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus = JsonStatus.NOT_INITIALISED
            return "Empty URL"
        }

        try {
            downloadStatus = JsonStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val message = when (e) {
                is MalformedURLException -> {
                    downloadStatus = JsonStatus.NOT_INITIALISED
                    "doInBackground: Invalid URL"
                }
                is IOException -> {
                    downloadStatus = JsonStatus.FAILED_OR_EMPTY
                    "doInBackground: IOException"
                }
                is SecurityException -> {
                    downloadStatus = JsonStatus.PERMISSIONS_ERROR
                    "doInBackground: SecurityException"
                }
                else -> {
                    downloadStatus = JsonStatus.ERROR
                    "Unknown error: ${e.message}"
                }
            }
            Log.e(TAG, message)
            return message
        }
    }

    override fun onPostExecute(result: String) {
        Log.d(TAG, "onPostExecute called")
        listener.onDownloadComplete(result, downloadStatus)
    }

}

