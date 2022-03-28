package pl.artimerek.flickerdownloader.download

import pl.artimerek.flickerdownloader.enum.JsonStatus

interface OnDownloadComplete {
    fun onDownloadComplete(data: String, status: JsonStatus)
}