package com.github.ferum_bot.games_rawg.core.models

import com.github.ferum_bot.games_rawg.core.enums.CategoryTypes

/**
 * Created by Matvey Popov.
 * Date: 09.02.2021
 * Time: 22:21
 * Project: Games-RAWG
 */
data class GameCategoryModel(
    val title: String = "The most Anticipated",
    val categoryType: CategoryTypes = CategoryTypes.LatestReleases,
    val listOfGames: List<Game> = listOf()
)
