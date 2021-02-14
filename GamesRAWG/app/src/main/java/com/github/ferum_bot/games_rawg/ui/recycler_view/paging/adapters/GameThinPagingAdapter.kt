package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.interfaces.PagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameThinViewHolder

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:54
 * Project: Games-RAWG
 */
class GameThinPagingAdapter: PagingDataAdapter<GameThinItem, PagingGameThinViewHolder>(
    MAIN_SCREEN_DIFF_CALL_BACK
), PagingAdapter {

    override fun onBindViewHolder(holder: PagingGameThinViewHolder, position: Int) {
        val game = getItem(position)
        game?.let {
            holder.bind(game)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingGameThinViewHolder {
        return PagingGameThinViewHolder.create(parent)
    }


    companion object {
        private val MAIN_SCREEN_DIFF_CALL_BACK = object: DiffUtil.ItemCallback<GameThinItem>() {
            override fun areItemsTheSame(oldItem: GameThinItem, newItem: GameThinItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameThinItem, newItem: GameThinItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}