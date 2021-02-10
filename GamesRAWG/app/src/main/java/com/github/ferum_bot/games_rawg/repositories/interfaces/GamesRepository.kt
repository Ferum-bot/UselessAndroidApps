package com.github.ferum_bot.games_rawg.repositories.interfaces

import androidx.paging.PagingData
import com.github.ferum_bot.core_network.di.components.NetworkComponent
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import kotlinx.coroutines.flow.Flow

/**
 * Created by Matvey Popov.
 * Date: 09.02.2021
 * Time: 22:18
 * Project: Games-RAWG
 */
interface GamesRepository {

    val component: NetworkComponent

    fun getDataFlowLink(categoryType: CategoryTypes, periodOfDate: GamePeriodOfDate): Flow<PagingData<Game>>

}