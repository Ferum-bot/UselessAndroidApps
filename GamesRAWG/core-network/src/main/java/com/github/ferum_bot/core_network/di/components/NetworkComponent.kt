package com.github.ferum_bot.core_network.di.components

import com.github.ferum_bot.core_network.api.RAWGApi
import com.github.ferum_bot.core_network.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 22:46
 * Project: Games-RAWG
 */

@Singleton
@Component(modules = [
    NetworkModule::class
])
interface NetworkComponent {

    fun getApi(): RAWGApi

    companion object {
        fun create(): NetworkComponent {
            return DaggerNetworkComponent.builder()
                .build()
        }
    }
}