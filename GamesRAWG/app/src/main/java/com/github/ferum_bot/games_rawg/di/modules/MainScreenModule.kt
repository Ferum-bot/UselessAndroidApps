package com.github.ferum_bot.games_rawg.di.modules

import androidx.lifecycle.ViewModel
import com.github.ferum_bot.games_rawg.di.annotations.ViewModelKey
import com.github.ferum_bot.games_rawg.di.scopes.ScreenScope
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractor
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractorImpl
import com.github.ferum_bot.games_rawg.repositories.implementations.GamesRepositoryImpl
import com.github.ferum_bot.games_rawg.repositories.interfaces.GamesRepository
import com.github.ferum_bot.games_rawg.viewmodels.main_screen.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:27
 * Project: Games-RAWG
 */
@Module
abstract class MainScreenModule {

    @Binds
    @ScreenScope
    abstract fun bindGamesRepository(repositoryImpl: GamesRepositoryImpl): GamesRepository

    @Binds
    @ScreenScope
    abstract fun bindMainScreenInteractor(interactorImpl: MainScreenInteractorImpl): MainScreenInteractor

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

}