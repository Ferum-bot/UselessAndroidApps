package com.github.ferum_bot.games_rawg.core.enums

import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters

/**
 * Created by Matvey Popov.
 * Date: 09.02.2021
 * Time: 22:24
 * Project: Games-RAWG
 */
sealed class CategoryTypes {
    object MostAnticipated : CategoryTypes()
    object LatestReleases : CategoryTypes()
    object Rated : CategoryTypes()

    data class Genre(
        val genreTypes: GamesApiParameters.GenreTypes
    ): CategoryTypes()
}