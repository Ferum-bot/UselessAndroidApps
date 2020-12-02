package com.example.criminalintent.ui.crimeList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.criminalintent.database.entities.Crime
import com.example.criminalintent.repository.AppRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import java.util.*

class CrimeListViewModel(private val appContext: Context): ViewModel() {

    private val repository = AppRepository.getInstance(appContext)

    private val _moveToEditFragmentEvent: MutableLiveData<Boolean> = MutableLiveData()
    val moveToEditFragmentEvent: LiveData<Boolean>
    get() = _moveToEditFragmentEvent

    var posToMove: Int?

    init {
        _moveToEditFragmentEvent.value = false
        posToMove = null
    }

    fun setMoveToEditFragmentEvent(value: Boolean) {
        _moveToEditFragmentEvent.value = value
    }

    val crimes: LiveData<List<Crime>>
        get() = repository.allCrimes


    fun deleteCrime(crime: Crime) {
        repository.deleteCrime(crime)
    }

}