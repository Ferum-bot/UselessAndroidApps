package com.github.ferum_bot.games_rawg.interactors.main_screen

import androidx.paging.PagingData
import androidx.paging.map
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.GameWideItem
import com.github.ferum_bot.games_rawg.core.toGameThinItem
import com.github.ferum_bot.games_rawg.core.toGameWideItem
import com.github.ferum_bot.games_rawg.repositories.interfaces.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
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

    override fun getMostAnticipatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameThinItem>> {
        val category = CategoryTypes.MostAnticipated
        return gamesRepository.getDataFlowLink(category, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameThinItem() }
        }
    }

    override fun getLatestReleasesGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>> {
        val category = CategoryTypes.LatestReleases
        return gamesRepository.getDataFlowLink(category, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameWideItem() }
        }
    }

    override fun getRatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>> {
        val category = CategoryTypes.Rated
        return gamesRepository.getDataFlowLink(category, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameWideItem() }
        }
    }

    override fun getGenreFlow(genre: CategoryTypes.Genre): Flow<PagingData<GameWideItem>> {
        TODO("Not yet implemented")
    }
}