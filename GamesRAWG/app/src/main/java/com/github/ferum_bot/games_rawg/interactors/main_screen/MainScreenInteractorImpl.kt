package com.github.ferum_bot.games_rawg.interactors.main_screen

import androidx.paging.PagingData
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.repositories.interfaces.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:41
 * Project: Games-RAWG
 */
class MainScreenInteractorImpl @Inject constructor(
    private val gamesRepository: GamesRepository
): MainScreenInteractor {

    override fun getMostAnticipatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>> {
        val category = CategoryTypes.MostAnticipated
        return gamesRepository.getDataFlowLink(category, periodOfDate)
    }

    override fun getLatestReleasesGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>> {
        val category = CategoryTypes.LatestReleases
        return gamesRepository.getDataFlowLink(category, periodOfDate)
    }

    override fun getRatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>> {
        val category = CategoryTypes.Rated
        return gamesRepository.getDataFlowLink(category, periodOfDate)
    }

    override fun getGenreFlow(genre: CategoryTypes.Genre): Flow<PagingData<Game>> {
        TODO("Not yet implemented")
    }
}