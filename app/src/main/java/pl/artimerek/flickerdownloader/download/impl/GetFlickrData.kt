package pl.artimerek.flickerdownloader.download.impl

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import pl.artimerek.flickerdownloader.download.OnDataAvailable
import pl.artimerek.flickerdownloader.model.Photo

private const val TAG = "GetFlickrData"

class GetFlickrData(private val lister: OnDataAvailable) : AsyncTask<String, Void, ArrayList<Photo>>() {

    override fun doInBackground(vararg params: String): ArrayList<Photo> {
        Log.d(TAG, "doInBackground starts")
        try{
            val jsonData = JSONObject(params[0])
            val items = jsonData.getJSONArray("items")

            for (i in 0 until items.length()){
                //  TODO json properties
            }
        }
    }

    override fun onPostExecute(result: ArrayList<Photo>?) {
        Log.d(TAG, "onPostExecute starts")
        super.onPostExecute(result)
    }
}