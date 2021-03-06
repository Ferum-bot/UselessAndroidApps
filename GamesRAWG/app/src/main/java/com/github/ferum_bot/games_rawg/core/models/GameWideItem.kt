package com.github.ferum_bot.games_rawg.core.models

import androidx.constraintlayout.widget.Placeholder
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 22:34
 * Project: Games-RAWG
 */
data class GameWideItem(
    val game: Game = Game(),
    val isPlaceholder: Boolean = false
): ListItem {

    override val itemId: Int
        get() = id

    val id: Int
        get() = game.id
    val title: String
        get() = game.title
    val backgroundImageURL: String?
        get() = game.backgroundImageURL

    companion object {
        private const val DEFAULT_SIZE_OF_PLACEHOLDERS_LIST = 5

        fun getListOfPlaceholders(size: Int = DEFAULT_SIZE_OF_PLACEHOLDERS_LIST) =
            List(size) { GameWideItem(isPlaceholder = true) }
    }
}
