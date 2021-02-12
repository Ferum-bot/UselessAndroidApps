package com.github.ferum_bot.games_rawg.core.models

import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.core.enums.SizeTypes
import com.github.ferum_bot.games_rawg.databinding.ItemGamesHorizontalBinding
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameThinPagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameWidePagingAdapter
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:20
 * Project: Games-RAWG
 */
data class HorizontalGameListItem(
    val id: Int,
    val title: String,
    val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    val typeOfListViewHolders: SizeTypes,
)  {

    fun setUpAdapterToBinding(binding: ItemGamesHorizontalBinding) {
        binding.titleTextView.text = title
        when(typeOfListViewHolders) {
            SizeTypes.WIDE -> {
                setUpThinAdapter(binding)
            }
            SizeTypes.THIN -> {
                setUpWideAdapter(binding)
            }
        }
    }

    private fun setUpThinAdapter(binding: ItemGamesHorizontalBinding) {
        val adapter = this.adapter as GameThinPagingAdapter
        binding.recyclerView.adapter = adapter
    }

    private fun setUpWideAdapter(binding: ItemGamesHorizontalBinding) {
        val adapter = this.adapter as GameWidePagingAdapter
        binding.recyclerView.adapter = adapter
    }
}
