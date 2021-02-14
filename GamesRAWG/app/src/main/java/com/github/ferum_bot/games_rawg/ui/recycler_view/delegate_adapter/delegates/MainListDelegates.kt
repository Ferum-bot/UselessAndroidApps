package com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.delegates


import com.github.ferum_bot.games_rawg.core.models.HorizontalGameThinListItem
import com.github.ferum_bot.games_rawg.core.models.HorizontalGameWideListItem
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem
import com.github.ferum_bot.games_rawg.databinding.ItemGamesHorizontalBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:27
 * Project: Games-RAWG
 */
object MainListDelegates {

    fun horizontalGamesWideListItemDelegate() =
        adapterDelegateViewBinding<HorizontalGameWideListItem, ListItem, ItemGamesHorizontalBinding>(
            { inflater, container -> ItemGamesHorizontalBinding.inflate(inflater, container, false) }
        ) {

            bind {
                item.setUpAdapterToBinding(binding)
            }
        }

    fun horizontalGamesThinListItemDelegate() =
        adapterDelegateViewBinding<HorizontalGameThinListItem, ListItem, ItemGamesHorizontalBinding>(
            { inflater, container -> ItemGamesHorizontalBinding.inflate(inflater, container, false) }
        ) {

            bind {
                item.setUpAdapterToBinding(binding)
            }
        }

}