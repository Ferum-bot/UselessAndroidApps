package com.github.ferum_bot.games_rawg.repositories.base

import androidx.paging.PagingData
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.core_network.model.GameVO
import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GameCategoryModel
import com.github.ferum_bot.games_rawg.core.models.GamePeriodOfDate
import kotlinx.coroutines.flow.Flow

/**
 * Created by Matvey Popov.
 * Date: 09.02.2021
 * Time: 22:18
 * Project: Games-RAWG
 */
interface GamesRepository {

    fun getDataFlowLink(categoryType: CategoryTypes, parameters: GamesApiParameters): Flow<GameCategoryModel>

}