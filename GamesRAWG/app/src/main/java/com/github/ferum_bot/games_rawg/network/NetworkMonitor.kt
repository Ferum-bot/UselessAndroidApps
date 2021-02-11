package com.github.ferum_bot.games_rawg.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission
import com.github.ferum_bot.games_rawg.core.Variables

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 22:55
 * Project: Games-RAWG
 */
class NetworkMonitor
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
constructor(private val appContext: Context){

    fun startNetworkCallBack() {
        val manager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        manager.registerNetworkCallback(
            builder.build(),
            object: ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    Variables.isNetworkConnectionAvailable = true
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    Variables.isNetworkConnectionAvailable = false
                }
            }
        )
    }

    fun stopNetworkCallback() {
        val manager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }
}