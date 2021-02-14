package com.github.ferum_bot.games_rawg.di.modules

import androidx.lifecycle.ViewModel
import com.github.ferum_bot.games_rawg.di.annotations.ViewModelKey
import com.github.ferum_bot.games_rawg.di.scopes.ScreenScope
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractor
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractorImpl
import com.github.ferum_bot.games_rawg.repositories.implementations.CustomRatingGamesRepository
import com.github.ferum_bot.games_rawg.repositories.implementations.GenresRepository
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
open class MainScreenModule {

    @Provides
    @ScreenScope
    fun provideMainScreenInterator(customRatingGamesRepository: CustomRatingGamesRepository, genresRepository: GenresRepository): MainScreenInteractor {
        return MainScreenInteractorImpl(customRatingGamesRepository, genresRepository)
    }

}