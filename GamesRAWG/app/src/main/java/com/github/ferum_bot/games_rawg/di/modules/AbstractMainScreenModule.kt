package com.github.ferum_bot.games_rawg.di.modules

import androidx.lifecycle.ViewModel
import com.github.ferum_bot.games_rawg.di.annotations.ViewModelKey
import com.github.ferum_bot.games_rawg.viewmodels.main_screen.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Matvey Popov.
 * Date: 14.02.2021
 * Time: 12:17
 * Project: Games-RAWG
 */
@Module
abstract class AbstractMainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel
    
}