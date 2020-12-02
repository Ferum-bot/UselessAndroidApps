package com.example.criminalintent.ui.crimeEdit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.criminalintent.database.entities.Crime

class CrimeEditViewModelFactory(private val appContext: Context,
                                private val crime: Crime?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrimeEditViewModel::class.java)) {
            return CrimeEditViewModel(appContext, crime) as T
        }
        throw IllegalArgumentException("Unexpected ViewModel")
    }
}