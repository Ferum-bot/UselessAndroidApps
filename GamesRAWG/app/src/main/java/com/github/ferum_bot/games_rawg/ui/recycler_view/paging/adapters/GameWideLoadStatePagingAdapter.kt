package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameWideLoadStateViewHolder

/**
 * Created by Matvey Popov.
 * Date: 15.02.2021
 * Time: 22:49
 * Project: Games-RAWG
 */
class GameWideLoadStatePagingAdapter(
    private val retry: () -> Unit,
    private val onErrorMessage: (String) -> Unit
): LoadStateAdapter<PagingGameWideLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PagingGameWideLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingGameWideLoadStateViewHolder {
        return PagingGameWideLoadStateViewHolder.create(parent, retry, onErrorMessage)
    }
}