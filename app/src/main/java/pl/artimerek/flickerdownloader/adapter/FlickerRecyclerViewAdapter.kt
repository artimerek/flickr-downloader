package pl.artimerek.flickerdownloader.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
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

        if (photos.isEmpty()) {
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.setText(R.string.search_no_results)
        } else {
            val photo = photos[position]
            Log.d(TAG, "onBindViewHolder ${photo.title}")
            Picasso.with(holder.thumbnail.context)
                .load(photo.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)

            holder.title.text = photo.title
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return if (photos.isNotEmpty()) photos.size else 1
    }
}