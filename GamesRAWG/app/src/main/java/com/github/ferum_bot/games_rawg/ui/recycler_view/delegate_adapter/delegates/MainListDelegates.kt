package com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.delegates

import com.github.ferum_bot.games_rawg.core.models.HorizontalGameListItem
import com.github.ferum_bot.games_rawg.databinding.ItemGamesHorizontalBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:27
 * Project: Games-RAWG
 */
object MainListDelegates {

    fun horizontalGamesListItemDelegate() =
        adapterDelegateViewBinding<HorizontalGameListItem, HorizontalGameListItem, ItemGamesHorizontalBinding>(
            { inflater, container -> ItemGamesHorizontalBinding.inflate(inflater, container, false) }
        ) {

            bind {
                item.setUpAdapterToBinding(binding)
            }
        }

}