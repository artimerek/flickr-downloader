package pl.artimerek.flickerdownloader.download.impl

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import pl.artimerek.flickerdownloader.download.OnDataAvailable
import pl.artimerek.flickerdownloader.model.Photo

private const val TAG = "GetFlickrData"

class GetFlickrData(private val lister: OnDataAvailable) : AsyncTask<String, Void, ArrayList<Photo>>() {

    override fun doInBackground(vararg params: String): ArrayList<Photo> {
        Log.d(TAG, "doInBackground starts")

        val photos = ArrayList<Photo>()

        try{
            val jsonData = JSONObject(params[0])
            val items = jsonData.getJSONArray("items")

            for (i in 0 until items.length()){
                val jsonPhoto = items.getJSONObject(i)
                val title = jsonPhoto.getString("title")
                val author = jsonPhoto.getString("author")
                val authorId = jsonPhoto.getString("author_id")
                val tags = jsonPhoto.getString("tags")
                val jsonMedia = jsonPhoto.getJSONObject("media")
                val photoUrl = jsonMedia.getString("m")
                val link = photoUrl.replaceFirst("_m.jgp", "_b.jpg")

                val photo = Photo(title, author, authorId, link, tags, photoUrl)

                photos.add(photo)
                Log.d(TAG, "doInBackground $photo")
            }
        }catch (e: JSONException) {
            e.printStackTrace()
            Log.e(TAG, "doInBackground ${e.message}")
            cancel(true)
            lister.onError(e)
        }
        return photos
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Log.d(TAG, "onPostExecute starts")
        super.onPostExecute(result)
        lister.onDataAvailable(result)
        Log.d(TAG, "onPostExecute ends")
    }
}