package com.github.ferum_bot.core_network.api.parameters

import javax.inject.Inject

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 22:27
 * Project: Games-RAWG
 */
data class GamesApiParameters(
    val dates: String? = null,
    val genre: GenreTypes? = null,
    val ordering: OrderingTypes? = null,
) {

    fun convertToMap(): Map<String, String> = mutableMapOf<String, String>().apply {
        dates?.let { put("dates", it) }
        genre?.let { put("genres", it.string) }
        ordering?.let { put("ordering", it.string) }
    }

    enum class GenreTypes(val string: String, val id: Int) {
        RACING("racing", 1), SHOOTER("shooter", 2),
        ADVENTURE("adventure", 3), ACTION("action", 4),
        RPG("rpg", 5), FIGHTING("fighting", 6),
        PUZZLE("puzzle", 7), STRATEGY("strategy", 10);
    }

    enum class OrderingTypes(val string: String) {
        BY_NAME("name"), BY_DATE_OF_RELEASE("released"),
        BY_DATE_OF_ADDING("added"), BY_DATE_OF_CREATIONS("created"),
        BY_DATE_OF_UPDATING("updated"), BY_RATING("rating"),
        BY_METACRITIC("metacritic"),

        BY_NAME_INVERTED("-name"), BY_DATE_OF_RELEASE_INVERTED("-released"),
        BY_DATE_OF_ADDING_INVERTED("-added"), BY_DATE_OF_CREATIONS_INVERTED("-created"),
        BY_DATE_OF_UPDATING_INVERTED("-updated"), BY_RATING_INVERTED("-rating"),
        BY_METACRITIC_INVERTED("-metacritic");
    }
}
