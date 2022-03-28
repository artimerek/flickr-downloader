package pl.artimerek.flickerdownloader.download

import pl.artimerek.flickerdownloader.model.Photo
import java.lang.Exception

interface OnDataAvailable {
    fun onDataAvailable(data: List<Photo>)
    fun onError(exception: Exception)
}