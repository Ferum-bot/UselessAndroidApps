package com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.github.ferum_bot.games_rawg.core.models.HorizontalGameListItem
import com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.delegates.MainListDelegates
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:03
 * Project: Games-RAWG
 */
class MainScreenAdapter: AsyncListDifferDelegationAdapter<HorizontalGameListItem>(BASE_DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(MainListDelegates.horizontalGamesListItemDelegate())
    }


    companion object {
        private val BASE_DIFF_CALLBACK = object: DiffUtil.ItemCallback<HorizontalGameListItem>() {
            override fun areItemsTheSame(oldItem: HorizontalGameListItem, newItem: HorizontalGameListItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HorizontalGameListItem, newItem: HorizontalGameListItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}