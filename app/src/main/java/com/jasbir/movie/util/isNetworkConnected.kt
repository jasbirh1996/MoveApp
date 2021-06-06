package com.jasbir.movie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object isNetworkConnected {

    fun isNetworkConnected(context : Context): Boolean{

        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                result = checkNetworkconnection(this,this.activeNetwork)
            }else{
                val networks = this.allNetworks
                for(network in networks){
                    if(checkNetworkconnection(this,network)){
                        result = true
                    }
                }
            }
        }
            return result
    }

    private fun checkNetworkconnection(conectivitymanager : ConnectivityManager,
    network : Network?): Boolean {
        conectivitymanager.getNetworkCapabilities(network)?.also {

            if(it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                return true


            }else if(it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                return  true

            }

        }
        return false
    }
}