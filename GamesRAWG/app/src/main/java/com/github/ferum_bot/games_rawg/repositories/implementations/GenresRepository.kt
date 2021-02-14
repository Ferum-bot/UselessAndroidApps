package com.github.ferum_bot.games_rawg.repositories.implementations

import androidx.paging.PagingData
import com.github.ferum_bot.core_network.di.components.NetworkComponent
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import com.github.ferum_bot.games_rawg.di.DI
import com.github.ferum_bot.games_rawg.repositories.interfaces.GamesRepository
import kotlinx.coroutines.flow.Flow
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
        TODO("Not yet implemented")
    }
}