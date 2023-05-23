package com.jasbir.movieapp.ui.View.UserInterface

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.jasbir.movieapp.R
import com.jasbir.movieapp.databinding.ActivityMainBinding
import com.jasbir.movieapp.ui.View.Adapter.NowPlayingMovieAdapter
import com.jasbir.movieapp.ui.View.Adapter.UpcommingMovieAdapter
import com.jasbir.movieapp.ui.ViewModel.MovieViewmodel
import com.jasbir.movieapp.util.isNetworkConnected


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appUpdateManager : AppUpdateManager
    private  val MY_REQUEST_CODE : Int = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternet(this)
        appUpdateManager = AppUpdateManagerFactory.create(this)
        checkUpdate()
}

    private val listener: InstallStateUpdatedListener? = InstallStateUpdatedListener { installState ->
        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            // After the update is downloaded, show a notification
            // and request user confirmation to restart the app.
            Log.d(ContentValues.TAG, "An update has been downloaded")
            Toast.makeText(this,"Downloaded", Toast.LENGTH_SHORT).show()
            appUpdateManager.completeUpdate()
        }
    }
    private fun checkUpdate() {
        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager?.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        Log.d(ContentValues.TAG, "Checking for updates")
        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                // Request the update.
                Log.d(ContentValues.TAG, "Update available")

                appUpdateManager.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                    AppUpdateType.FLEXIBLE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    MY_REQUEST_CODE)
            } else {
                Log.d(ContentValues.TAG, "No Update available")
            }
        }
        appUpdateManager.registerListener(listener!!)
    }

    fun checkInternet(context : Context){
        if(isNetworkConnected.isNetworkConnected(context)){
            intializing()
        }else{
            startActivity(Intent(this,NoInterNet::class.java))
            finish()
        }

    }
    fun intializing(){
        val viewModel = ViewModelProvider(this).get(MovieViewmodel::class.java)
        viewModel.response.observe(this, Observer {

           binding.rvNowPlaying.apply {
               layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = NowPlayingMovieAdapter(it)

            }
            
        })

        viewModel.trending.observe(this, Observer {
          binding.rvTrending.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = UpcommingMovieAdapter(it)

            }
        })
        binding.viewall.setOnClickListener {
            startActivity(Intent(this,ViewAll::class.java))
        }
    }



}