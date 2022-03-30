package pl.artimerek.flickerdownloader.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.view.Menu
import android.widget.SearchView
import pl.artimerek.flickerdownloader.R

private const val TAG = "SearchActivity"

class SearchActivity : BaseActivity() {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolbar(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchableInfo = searchManager.getSearchableInfo(componentName)

        searchView?.setSearchableInfo(searchableInfo)
        searchView?.isIconified = true
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                val shared = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                shared.edit().putString(FLICKR_QUERY, p0).apply()
                searchView?.clearFocus()

                finish()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener {
            finish()
            false
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return true
    }
}