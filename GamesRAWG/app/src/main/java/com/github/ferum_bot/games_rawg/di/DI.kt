package com.github.ferum_bot.games_rawg.di

import com.github.ferum_bot.core_network.di.components.DaggerNetworkComponent
import com.github.ferum_bot.core_network.di.components.NetworkComponent

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:14
 * Project: Games-RAWG
 */
object DI {

    val networkComponent by lazy {
        DaggerNetworkComponent.create()
    }

}