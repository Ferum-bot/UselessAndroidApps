package com.example.rickandmorty.network

import android.net.ConnectivityManager
import android.net.Network
import com.example.rickandmorty.core.Variables

class MyNetworkCallback: ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Variables.isNetworkConnectionAvailable = true
    }


    override fun onLost(network: Network) {
        super.onLost(network)
        Variables.isNetworkConnectionAvailable = false
    }
}