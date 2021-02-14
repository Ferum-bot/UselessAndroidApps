package com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem
import com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.delegates.MainListDelegates
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:03
 * Project: Games-RAWG
 */
class MainScreenAdapter: AsyncListDifferDelegationAdapter<ListItem>(BASE_DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(MainListDelegates.horizontalGamesThinListItemDelegate())
            .addDelegate(MainListDelegates.horizontalGamesWideListItemDelegate())
    }


    companion object {
        private val BASE_DIFF_CALLBACK = object: DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.itemId == newItem.itemId
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}