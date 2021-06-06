package com.jasbir.movie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.lifecycle.LiveData

class NetworkConnection (private val context : Context) : LiveData<Boolean> (){

    private var connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    private  lateinit var networkCallback: ConnectivityManager.NetworkCallback

    private fun connectivitManagerCallback(): ConnectivityManager.NetworkCallback{
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            networkCallback = object : ConnectivityManager.NetworkCallback(){
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }
            return networkCallback
        }else
        {
            throw IllegalAccessError("Error")
        }
    }

}