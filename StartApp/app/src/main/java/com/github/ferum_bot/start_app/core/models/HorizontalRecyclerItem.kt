package com.github.ferum_bot.start_app.core.models

/**
 * Created by Matvey Popov.
 * Date: 27.12.2020
 * Time: 21:41
 * Project: StartApp
 */
data class HorizontalRecyclerItem(
    val firstLetter: String = "A",
    val listOfItems: List<HorizontalListItem> = listOf()
): BaseListItem
