package com.github.ferum_bot.games_rawg.application

import android.app.Application
import com.github.ferum_bot.games_rawg.network.NetworkMonitor

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 23:54
 * Project: Games-RAWG
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setNetworkConnectionObserver()
    }

    private fun setNetworkConnectionObserver() {
        val monitor = NetworkMonitor(applicationContext)
        monitor.startNetworkCallBack()
    }
}