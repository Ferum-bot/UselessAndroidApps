package com.github.ferum_bot.games_rawg.core.util.implementations

import android.content.Context
import com.github.ferum_bot.games_rawg.core.util.interfaces.ResourceProvider
import javax.inject.Inject

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:33
 * Project: Games-RAWG
 */
class AndroidResourceProvider @Inject constructor(
    private val context: Context
): ResourceProvider {

    override fun getString(id: Int): String
        = context.getString(id)
}