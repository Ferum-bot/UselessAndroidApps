package com.github.ferum_bot.games_rawg.core.models

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.core.enums.SizeTypes
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem
import com.github.ferum_bot.games_rawg.databinding.ItemGamesHorizontalBinding
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GamePagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameThinPagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameWidePagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameThinViewHolder
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameWideViewHolder
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:20
 * Project: Games-RAWG
 */
data class HorizontalGameListItem<out T: ListItem, out VH: RecyclerView.ViewHolder>(
    val id: Int,
    val title: String,
    val adapter: GamePagingAdapter<T, VH>,
    val typeOfListViewHolders: SizeTypes,
)  {

    companion object Builder {
        private var currentUnoccupiedId = Int.MIN_VALUE

        fun provideThinHorizontalListItemWithTitle(title: String): HorizontalGameListItem<GameThinItem, PagingGameThinViewHolder> {
            return HorizontalGameListItem(
                currentUnoccupiedId,
                title,
                GamePagingAdapter<GameThinItem, PagingGameThinViewHolder>(),
                SizeTypes.THIN
            ).also { currentUnoccupiedId++ }
        }

        fun provideWideHorizontalListItemWithTitle(title: String): HorizontalGameListItem<GameWideItem, PagingGameWideViewHolder> {
            return HorizontalGameListItem(
                currentUnoccupiedId,
                title,
                GamePagingAdapter<GameWideItem, PagingGameWideViewHolder>(),
                SizeTypes.WIDE
            ).also { currentUnoccupiedId++ }
        }
    }
}
