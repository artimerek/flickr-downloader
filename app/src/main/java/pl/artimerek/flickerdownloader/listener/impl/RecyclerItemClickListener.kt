package pl.artimerek.flickerdownloader.listener.impl

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import pl.artimerek.flickerdownloader.listener.OnRecyclerClickListener

private const val TAG = "RecyclerItemClickListener"

class RecyclerItemClickListener(context: Context,
                                recyclerView: RecyclerView,
                                private val listener: OnRecyclerClickListener)
    : RecyclerView.SimpleOnItemTouchListener(){

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent $e")
        return super.onInterceptTouchEvent(rv, e)
    }
}