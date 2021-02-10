package com.github.ferum_bot.games_rawg.core.util.interfaces

import androidx.annotation.StringRes

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:32
 * Project: Games-RAWG
 */
interface ResourceProvider {

    fun getString(@StringRes id: Int): String

}