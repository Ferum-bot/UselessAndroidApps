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
 * Date: 14.02.2021
 * Time: 12:11
 * Project: Games-RAWG
 */
class GenresRepository @Inject constructor(): GamesRepository {
    override val component: NetworkComponent
        get() = DI.networkComponent

    override fun getDataFlowLink(
        categoryType: CategoryTypes,
        periodOfDate: GamePeriodOfDate
    ): Flow<PagingData<Game>> {
        val service = component.getApi()
        val parameters = when(categoryType) {
            is CategoryTypes.Genre -> {
                GamesApiParameters(
                    periodOfDate.toString(),
                    categoryType.genreTypes,
                    GamesApiParameters.OrderingTypes.BY_RATING_INVERTED
                )
            }
            else -> {
                throw IllegalArgumentException("Invalid Category type: $categoryType")
            }
        }
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = { GamesPagingSource(service, parameters) }
        ).flow.map { pagingSource ->
            pagingSource.map { it.toGame() }
        }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}