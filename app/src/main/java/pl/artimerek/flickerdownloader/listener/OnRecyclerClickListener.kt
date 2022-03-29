package pl.artimerek.flickerdownloader.listener

import android.view.View

interface OnRecyclerClickListener {
    fun onItemClick(view: View, position: Int)
    fun onItemLongClick(view: View, position: Int)
}