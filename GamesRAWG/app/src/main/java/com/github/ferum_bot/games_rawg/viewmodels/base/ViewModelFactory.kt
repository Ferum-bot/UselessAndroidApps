package com.github.ferum_bot.games_rawg.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:21
 * Project: Games-RAWG
 */
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass] ?:
                throw IllegalArgumentException("ViewModel not found: $modelClass")
        return viewModelProvider.get() as T
    }
}