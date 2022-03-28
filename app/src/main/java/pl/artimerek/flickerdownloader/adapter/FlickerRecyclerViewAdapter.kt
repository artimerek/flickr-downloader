package pl.artimerek.flickerdownloader.adapter

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.artimerek.flickerdownloader.R
import pl.artimerek.flickerdownloader.model.Photo

private const val TAG = "RecyclerViewAdapter"

class FlickerRecyclerViewAdapter(private var photos: List<Photo>) :
    RecyclerView.Adapter<FlickerImageViewHolder>() {

    fun loadNewData(newPhotos: List<Photo>) {
        photos = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photos.isNotEmpty()) photos[position] else null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickerImageViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickerImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickerImageViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return if (photos.isNotEmpty()) photos.size else 0
    }
}