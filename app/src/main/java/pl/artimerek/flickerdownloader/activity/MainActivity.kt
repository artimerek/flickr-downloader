package pl.artimerek.flickerdownloader.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import pl.artimerek.flickerdownloader.R
import pl.artimerek.flickerdownloader.databinding.ActivityMainBinding
import pl.artimerek.flickerdownloader.download.OnDataAvailable
import pl.artimerek.flickerdownloader.download.OnDownloadComplete
import pl.artimerek.flickerdownloader.download.impl.GetFlickrData
import pl.artimerek.flickerdownloader.download.impl.GetRawData
import pl.artimerek.flickerdownloader.enum.JsonStatus
import pl.artimerek.flickerdownloader.model.Photo
import java.lang.Exception

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), OnDownloadComplete, OnDataAvailable {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreateCalled")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val url = createUri(
            "https://api.flickr.com/services/feeds/photos_public.gne",
            "android,oreo",
            "en-us",
            true
        )


        val getRawData = GetRawData(this)
        getRawData.execute(url)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        Log.d(TAG, "onCreate ends")

    }

    private fun createUri(url: String, search: String, lang: String, matchAll: Boolean): String {
        Log.d(TAG, ".createUri starts")

        return Uri.parse(url)
            .buildUpon()
            .appendQueryParameter("tags", search)
            .appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
            .appendQueryParameter("lang", lang)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .build()
            .toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenuCalled")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelectedCalled")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDownloadComplete(data: String, status: JsonStatus) {
        if (status == JsonStatus.OK) {
            Log.d(TAG, "onDownloadComplete called,")
            val getFlickrData = GetFlickrData(this)
            getFlickrData.execute(data)
        } else {
            Log.d(TAG, "onDownloadComplete failed status $status, message $status")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable $data")

    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError ${exception.message}")
    }


}