package com.example.criminalintent.ui.crimeList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class CrimeListViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrimeListViewModel::class.java)) {
            return CrimeListViewModel(context) as T
        }
        throw IllegalArgumentException("Unexpected ViewModel")
    }
}