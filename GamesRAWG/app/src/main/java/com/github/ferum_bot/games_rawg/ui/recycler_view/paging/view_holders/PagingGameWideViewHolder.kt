package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.enums.SizeTypes
import com.github.ferum_bot.games_rawg.core.extensions.loadImageWithDefaultOptions
import com.github.ferum_bot.games_rawg.core.models.GameWideItem
import com.github.ferum_bot.games_rawg.databinding.ItemGameWideBinding

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 22:28
 * Project: Games-RAWG
 */
class PagingGameWideViewHolder(
    private val binding: ItemGameWideBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: GameWideItem) {
        binding.titleTextView.text = game.title
        loadImageWithDefaultOptions(
            binding.root,
            binding.imageView,
            game.backgroundImageURL,
            SizeTypes.WIDE
        )
    }

    companion object {
        fun create(parent: ViewGroup): PagingGameWideViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemGameWideBinding>(inflater, R.layout.item_game_wide, parent, false)
            return PagingGameWideViewHolder(binding)
        }
    }
}