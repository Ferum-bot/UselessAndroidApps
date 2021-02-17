package com.github.ferum_bot.games_rawg.interactors.main_screen

import androidx.paging.PagingData
import androidx.paging.map
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.GameWideItem
import com.github.ferum_bot.games_rawg.core.toGameThinItem
import com.github.ferum_bot.games_rawg.core.toGameWideItem
import com.github.ferum_bot.games_rawg.repositories.interfaces.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:41
 * Project: Games-RAWG
 */
class MainScreenInteractorImpl @Inject constructor(
    private val customRatingRepository: GamesRepository,
    private val genresRepository: GamesRepository,
): MainScreenInteractor {

    override fun getMostAnticipatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameThinItem>> {
        val category = CategoryTypes.MostAnticipated
        return customRatingRepository.getDataFlowLink(category, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameThinItem() }
        }
    }

    override fun getLatestReleasesGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>> {
        val category = CategoryTypes.LatestReleases
        return customRatingRepository.getDataFlowLink(category, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameWideItem() }
        }
    }

    override fun getRatedGamesFlow(periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>> {
        val category = CategoryTypes.Rated
        return customRatingRepository.getDataFlowLink(category, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameWideItem() }
        }
    }

    override fun getWideGenreFlow(genre: GamesApiParameters.GenreTypes, periodOfDate: GamePeriodOfDate): Flow<PagingData<GameWideItem>> {
        val categoryTypes = CategoryTypes.Genre(genre)
        return genresRepository.getDataFlowLink(categoryTypes, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameWideItem() }
        }
    }

    override fun getThinGenreFlow(genre: GamesApiParameters.GenreTypes, periodOfDate: GamePeriodOfDate): Flow<PagingData<GameThinItem>> {
        val categoryTypes = CategoryTypes.Genre(genre)
        return genresRepository.getDataFlowLink(categoryTypes, periodOfDate).map { pagingData ->
            pagingData.map { it.toGameThinItem() }
        }
    }
}