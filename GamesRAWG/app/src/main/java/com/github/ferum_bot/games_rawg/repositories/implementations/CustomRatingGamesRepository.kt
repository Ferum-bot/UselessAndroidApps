package com.github.ferum_bot.games_rawg.repositories.implementations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.github.ferum_bot.core_network.api.pagging.GamesPagingSource
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.core_network.di.components.NetworkComponent
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.core.toGame
import com.github.ferum_bot.games_rawg.di.DI
import com.github.ferum_bot.games_rawg.repositories.interfaces.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 23:02
 * Project: Games-RAWG
 */
class CustomRatingGamesRepository @Inject constructor(): GamesRepository {

    override val component: NetworkComponent
        get() = DI.networkComponent

    override fun getDataFlowLink(
        categoryType: CategoryTypes,
        periodOfDate: GamePeriodOfDate
    ): Flow<PagingData<Game>> {
        val service = component.getApi()
        val parameters = when(categoryType) {
            is CategoryTypes.LatestReleases -> {
                GamesApiParameters(
                    dates = periodOfDate.startDate
                )
            }
            is CategoryTypes.MostAnticipated -> {
                GamesApiParameters(
                    dates = periodOfDate.startDate,
                    ordering = GamesApiParameters.OrderingTypes.BY_DATE_OF_ADDING_INVERTED
                )
            }
            is CategoryTypes.Rated -> {
                GamesApiParameters(
                    dates = periodOfDate.startDate,
                    ordering = GamesApiParameters.OrderingTypes.BY_RATING_INVERTED
                )
            }
            else -> {
                throw IllegalStateException("Can't find custom top of games: $categoryType")
            }
        }
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { GamesPagingSource(service, parameters) }
        ).flow.map { pagingData ->
            pagingData.map { it.toGame() }
        }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}