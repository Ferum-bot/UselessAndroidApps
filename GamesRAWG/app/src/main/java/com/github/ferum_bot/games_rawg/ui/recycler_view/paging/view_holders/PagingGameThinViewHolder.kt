package com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.enums.ViewHoldersTypes
import com.github.ferum_bot.games_rawg.core.extensions.loadImageWithDefaultOptions
import com.github.ferum_bot.games_rawg.core.models.GameThinItem
import com.github.ferum_bot.games_rawg.databinding.ItemGameThinBinding

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:55
 * Project: Games-RAWG
 */
class PagingGameThinViewHolder(
    private val binding: ItemGameThinBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: GameThinItem) {
        binding.titleTextView.text = game.title
        loadImageWithDefaultOptions(
            binding.root,
            binding.imageView,
            game.backgroundImageURL,
            ViewHoldersTypes.THIN
        )
    }

    companion object {
        fun create(parent: ViewGroup): PagingGameThinViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemGameThinBinding>(inflater, R.layout.item_game_thin, parent, false)
            return PagingGameThinViewHolder(binding)
        }
    }
}