package com.github.ferum_bot.games_rawg.interactors.main_screen

import androidx.paging.Pager
import androidx.paging.PagingData
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.GameWideItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:41
 * Project: Games-RAWG
 */
interface MainScreenInteractor {

    fun getMostAnticipatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameThinItem>>

    fun getLatestReleasesGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>>

    fun getRatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>>

    fun getGenreFlow(genre: GamesApiParameters.GenreTypes, periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>>

}