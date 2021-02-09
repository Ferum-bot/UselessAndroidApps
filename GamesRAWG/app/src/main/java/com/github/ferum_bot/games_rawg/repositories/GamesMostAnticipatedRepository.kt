package com.github.ferum_bot.games_rawg.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.ferum_bot.core_network.api.pagging.GamesPagingSource
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.core_network.di.components.NetworkComponent
import com.github.ferum_bot.core_network.model.GameVO
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GameCategoryModel
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.repositories.base.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

/**
 * Created by Matvey Popov.
 * Date: 09.02.2021
 * Time: 22:17
 * Project: Games-RAWG
 */
class GamesMostAnticipatedRepository(): GamesRepository {
    private val component by lazy {
        NetworkComponent.create()
    }

    override fun getDataFlowLink(
        categoryType: CategoryTypes,
        parameters: GamesApiParameters
    ): Flow<GameCategoryModel> {
        val service = component.getApi()
        val pager = Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = { GamesPagingSource(service, parameters) }
        ).flow.map {
            it.mapSync { it }
        }
        val result = pager.transform<> {  }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20;
    }
}