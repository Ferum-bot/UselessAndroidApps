package com.github.ferum_bot.games_rawg.core

import com.github.ferum_bot.core_network.model.GameVO
import com.github.ferum_bot.games_rawg.core.models.Game
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.GameWideItem

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 11:46
 * Project: Games-RAWG
 */

fun GameVO.toGame(): Game {
    return Game(
        id,
        title,
        backgroundImageURL
    )
}

fun Game.toGameWideItem(): GameWideItem {
    return GameWideItem(
        this
    )
}
fun Game.toGameThinItem(): GameThinItem {
    return GameThinItem(
        this
    )
}