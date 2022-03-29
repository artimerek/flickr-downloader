package pl.artimerek.flickerdownloader.activity

import android.os.Bundle
import pl.artimerek.flickerdownloader.R

private const val TAG = "SearchActivity"

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolbar(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        return true
    }
}