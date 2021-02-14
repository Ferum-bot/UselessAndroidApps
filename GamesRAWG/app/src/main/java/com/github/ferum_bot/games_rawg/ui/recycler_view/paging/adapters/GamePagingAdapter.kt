package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.GameWideItem
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem
import com.github.ferum_bot.games_rawg.ui.recycler_view.MainScreenCallBack
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameThinViewHolder
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameWideViewHolder

/**
 * Created by Matvey Popov.
 * Date: 14.02.2021
 * Time: 12:41
 * Project: Games-RAWG
 */
class GamePagingAdapter<T: ListItem, VH: RecyclerView.ViewHolder>: PagingDataAdapter<T, VH>(MainScreenCallBack<T>()) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        val game = getItem(position)
        game?.let {
            when(holder) {
                is PagingGameThinViewHolder -> {
                    holder.bind(game as GameThinItem)
                }
                is PagingGameWideViewHolder -> {
                    holder.bind(game as GameWideItem)
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when(viewType) {
            R.layout.item_game_thin -> {
                PagingGameThinViewHolder.create(parent) as VH
            }
            R.layout.item_game_wide -> {
                PagingGameWideViewHolder.create(parent) as VH
            }
            else -> {
                throw IllegalArgumentException("Unexpected view type to create holder: $viewType")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(val game = getItem(position)) {
            is GameWideItem -> R.layout.item_game_wide
            is GameThinItem -> R.layout.item_game_thin
            else -> {
                throw IllegalArgumentException("Unexpected item view type: ${game.toString()}")
            }
        }
    }
}