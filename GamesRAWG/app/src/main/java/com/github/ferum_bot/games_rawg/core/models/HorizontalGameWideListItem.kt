package com.github.ferum_bot.games_rawg.core.models

import androidx.recyclerview.widget.ConcatAdapter
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem
import com.github.ferum_bot.games_rawg.databinding.ItemGamesHorizontalBinding
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameWidePagingAdapter

/**
 * Created by Matvey Popov.
 * Date: 14.02.2021
 * Time: 13:48
 * Project: Games-RAWG
 */
data class HorizontalGameWideListItem(
    val id: Int,
    val title: String,
    val adapter: GameWidePagingAdapter,
    val adapterWithFooter: ConcatAdapter
): ListItem {
    override val itemId: Int
        get() = id

    fun setUpAdapterToBinding(binding: ItemGamesHorizontalBinding) {
        binding.titleTextView.text = title
        binding.recyclerView.adapter = adapterWithFooter
    }
}