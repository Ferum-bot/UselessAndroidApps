package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.databinding.ItemGameWideBinding
import com.github.ferum_bot.games_rawg.databinding.ItemRefreshWideBinding

/**
 * Created by Matvey Popov.
 * Date: 15.02.2021
 * Time: 22:50
 * Project: Games-RAWG
 */
class PagingGameWideLoadStateViewHolder private constructor(
    private val binding: ItemRefreshWideBinding,
    private val retry: () -> Unit,
    private val onErrorMessage: (String) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            onErrorMessage.invoke(loadState.error.message ?: "Something went wrong")
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
    }

    companion object {

        fun create(parent: ViewGroup, retry: () -> Unit, onErrorMessage: (String) -> Unit): PagingGameWideLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemRefreshWideBinding>(inflater, R.layout.item_refresh_wide, parent, false)
            return PagingGameWideLoadStateViewHolder(binding, retry, onErrorMessage)
        }
    }
}