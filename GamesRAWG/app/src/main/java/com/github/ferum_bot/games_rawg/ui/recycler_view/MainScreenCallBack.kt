package com.github.ferum_bot.games_rawg.ui.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem

/**
 * Created by Matvey Popov.
 * Date: 14.02.2021
 * Time: 13:02
 * Project: Games-RAWG
 */
class MainScreenCallBack<T: ListItem>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.itemId == newItem.itemId
    }
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.equals(newItem)
    }
}