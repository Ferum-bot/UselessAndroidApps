package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.enums.SizeTypes
import com.github.ferum_bot.games_rawg.core.extensions.loadImageWithDefaultOptions
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.databinding.ItemGameThinBinding

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:55
 * Project: Games-RAWG
 */
class PagingGameThinViewHolder private constructor(
    private val binding: ItemGameThinBinding
): RecyclerView.ViewHolder(binding.root) {

    private val context: Context
        get() = binding.root.context

    fun bind(game: GameThinItem) {
        removePlaceholder()
        binding.titleTextView.text = game.title
        loadImageWithDefaultOptions(
            binding.root,
            binding.imageView,
            game.backgroundImageURL,
            SizeTypes.THIN
        )
    }

    fun bindPlaceholder() {
        setPlaceholder()
    }

    private fun setPlaceholder() {
        binding.imageView.setImageResource(R.drawable.bg_item_placeholder)
        binding.titleTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.placeholder))
        binding.shimmerLayout.startShimmer()
    }

    private fun removePlaceholder() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.hideShimmer()
        binding.titleTextView.background = binding.root.background
    }

    companion object {
        fun create(parent: ViewGroup): PagingGameThinViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemGameThinBinding>(inflater, R.layout.item_game_thin, parent, false)
            return PagingGameThinViewHolder(binding)
        }
    }
}