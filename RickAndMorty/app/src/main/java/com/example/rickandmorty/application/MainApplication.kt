package com.example.rickandmorty.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.example.rickandmorty.network.MyNetworkCallback
import timber.log.Timber

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        registerNetworkCallback()
    }

    override fun onTerminate() {
        super.onTerminate()
        removeNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val context = applicationContext
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        manager.registerNetworkCallback(
                builder.build(),
                MyNetworkCallback()
        )
    }

    private fun removeNetworkCallback() {
        val context = applicationContext
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }

}