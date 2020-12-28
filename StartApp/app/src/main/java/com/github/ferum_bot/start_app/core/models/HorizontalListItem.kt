package com.github.ferum_bot.start_app.core.models

import android.graphics.drawable.Drawable
import com.github.ferum_bot.start_app.R

/**
 * Created by Matvey Popov.
 * Date: 27.12.2020
 * Time: 21:54
 * Project: StartApp
 */
data class HorizontalListItem(
    val id: Int = 0,
    val title: String = "Title 1",
    val drawableIcon: Drawable? = null,
): BaseListItem
