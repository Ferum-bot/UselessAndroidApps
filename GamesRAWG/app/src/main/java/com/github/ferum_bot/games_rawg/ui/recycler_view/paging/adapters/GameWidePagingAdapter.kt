package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.GameWideItem
import com.github.ferum_bot.games_rawg.core.models.interfaces.PagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameWideViewHolder

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 22:43
 * Project: Games-RAWG
 */
class GameWidePagingAdapter: PagingDataAdapter<GameWideItem, PagingGameWideViewHolder>(
    MAIN_SCREEN_DIFF_CALL_BACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingGameWideViewHolder {
        return PagingGameWideViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PagingGameWideViewHolder, position: Int) {
        val game = getItem(position)
        if (game == null) {
            holder.bindPlaceholder()
        }
        else {
            holder.bind(game)
        }
    }

    companion object {
        private val MAIN_SCREEN_DIFF_CALL_BACK = object: DiffUtil.ItemCallback<GameWideItem>() {
            override fun areItemsTheSame(oldItem: GameWideItem, newItem: GameWideItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameWideItem, newItem: GameWideItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}