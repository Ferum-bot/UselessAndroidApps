package com.github.ferum_bot.games_rawg.interactors.main_screen

import androidx.paging.Pager
import androidx.paging.PagingData
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import kotlinx.coroutines.flow.Flow

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:41
 * Project: Games-RAWG
 */
interface MainScreenInteractor {

    fun getMostAnticipatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>>

    fun getLatestReleasesGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>>

    fun getRatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>>

    fun getGenreFlow(genre: CategoryTypes.Genre): Flow<PagingData<Game>>

}