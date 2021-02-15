package com.github.ferum_bot.games_rawg.core.models

import androidx.recyclerview.widget.ConcatAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.*

/**
 * Created by Matvey Popov.
 * Date: 12.02.2021
 * Time: 21:20
 * Project: Games-RAWG
 */
object HorizontalGameListItemBuilder{
    private var currentUnoccupiedId = Int.MIN_VALUE

    fun provideThinHorizontalListItemWithTitle(title: String, onErrorMessage: (String) -> Unit): HorizontalGameThinListItem {
        val adapter = GameThinPagingAdapter()
        return HorizontalGameThinListItem(
            currentUnoccupiedId,
            title,
            adapter,
            provideGameThinLoadStateAdapter(adapter, onErrorMessage)
        ).also { currentUnoccupiedId++ }
    }

    fun provideWideHorizontalListItemWithTitle(title: String, onErrorMessage: (String) -> Unit): HorizontalGameWideListItem {
        val adapter = GameWidePagingAdapter()
        return HorizontalGameWideListItem(
            currentUnoccupiedId,
            title,
            adapter,
            provideGameWideLoadStateAdapter(adapter, onErrorMessage)
        ).also { currentUnoccupiedId++ }
    }

    private fun provideGameWideLoadStateAdapter(adapter: GameWidePagingAdapter, onErrorMessage: (String) -> Unit): ConcatAdapter {
        return adapter.withLoadStateFooter(
            GameWideLoadStatePagingAdapter(adapter::retry, onErrorMessage)
        )
    }

    private fun provideGameThinLoadStateAdapter(adapter: GameThinPagingAdapter, onErrorMessage: (String) -> Unit): ConcatAdapter {
        return adapter.withLoadStateFooter(
            GameThinLoadStatePagingAdapter(adapter::retry, onErrorMessage)
        )
    }
}
