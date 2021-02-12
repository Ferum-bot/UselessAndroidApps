package com.github.ferum_bot.games_rawg.di

import com.github.ferum_bot.core_network.di.components.DaggerNetworkComponent
import com.github.ferum_bot.core_network.di.components.NetworkComponent
import com.github.ferum_bot.games_rawg.di.components.DaggerAppComponent
import com.github.ferum_bot.games_rawg.di.components.DaggerMainScreenComponent
import com.github.ferum_bot.games_rawg.di.components.MainScreenComponent

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:14
 * Project: Games-RAWG
 */
object DI {

    val networkComponent by lazy { NetworkComponent.create() }

    val appComponent by lazy { DaggerAppComponent.builder().build() }

    val mainScreenComponent by lazy { DaggerMainScreenComponent.builder().build() }
}