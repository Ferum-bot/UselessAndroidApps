package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.core.models.interfaces.PagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameThinViewHolder
import timber.log.Timber

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:54
 * Project: Games-RAWG
 */
class GameThinPagingAdapter: PagingDataAdapter<GameThinItem, PagingGameThinViewHolder>(
    MAIN_SCREEN_DIFF_CALL_BACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingGameThinViewHolder {
        return PagingGameThinViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PagingGameThinViewHolder, position: Int) {
        val game = getItem(position)
        if (game == null || game.isPlaceholder) {
            holder.bindPlaceholder()
        }
        else {
            holder.bind(game)
        }
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