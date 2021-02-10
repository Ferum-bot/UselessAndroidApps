package com.github.ferum_bot.games_rawg.di.components

import com.github.ferum_bot.games_rawg.di.modules.MainScreenModule
import com.github.ferum_bot.games_rawg.di.scopes.ScreenScope
import com.github.ferum_bot.games_rawg.viewmodels.base.ViewModelFactory
import dagger.Component

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:11
 * Project: Games-RAWG
 */
@Component(modules = [MainScreenModule::class])
@ScreenScope
interface MainScreenComponent {

    fun viewModelFactory(): ViewModelFactory



}