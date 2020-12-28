package com.github.ferum_bot.start_app.ui.fragments.main_list

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Matvey Popov.
 * Date: 27.12.2020
 * Time: 21:03
 * Project: StartApp
 */
class MainListViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainListViewModel::class.java)) {
            return MainListViewModel() as T
        }
        throw IllegalArgumentException("Unexpected View Model to create: ${modelClass.toString()}")
    }

}