package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameThinLoadStateViewHolder

/**
 * Created by Matvey Popov.
 * Date: 15.02.2021
 * Time: 22:47
 * Project: Games-RAWG
 */
class GameThinLoadStatePagingAdapter(
    private val retry: () -> Unit,
    private val onErrorMessage: (String) -> Unit
): LoadStateAdapter<PagingGameThinLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PagingGameThinLoadStateViewHolder, loadState: LoadState) {
       holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingGameThinLoadStateViewHolder {
        return PagingGameThinLoadStateViewHolder.create(parent, retry, onErrorMessage)
    }
}