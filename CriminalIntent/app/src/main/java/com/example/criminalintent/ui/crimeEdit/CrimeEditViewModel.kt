package com.example.criminalintent.ui.crimeEdit

import android.content.Context
import android.text.BoringLayout
import androidx.lifecycle.ViewModel
import com.example.criminalintent.database.entities.Crime
import com.example.criminalintent.repository.AppRepository
import java.util.*

class CrimeEditViewModel(private val appContext: Context,
                        private var crime: Crime? ): ViewModel() {

    private val repository: AppRepository

    private var title: String
    private var description: String

    private var isSolved: Boolean

    private var date: Date

    val crimes
    get() = repository.allCrimes

    init {
        repository = AppRepository.getInstance(appContext)

        title = ""
        description = ""

        isSolved = false

        date = Date()
    }

    fun getCurrentDate(): Date{
        return date
    }

    fun clear() {
        repository.clearAllCrimes()
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setSolved(isSolved: Boolean) {
        this.isSolved = isSolved
    }

    fun setDate(date: Date) {
        this.date = date
    }

    fun applyChanges() {
        if (crime == null) {
            insertCrime()
        }
        else {
            updateCrime()
        }
    }

    fun setCrime(crime: Crime) {
        this.crime = crime
    }

    private fun updateCrime() {
        val newCrime = getNewCrime()
        repository.updateCrime(newCrime)
    }

    private fun insertCrime() {
        val newCrime = getNewCrime()
        repository.insertCrime(newCrime)
    }

    private fun getNewCrime(): Crime {
        val currentCrime: Crime? = crime
            ?: return Crime(title = this.title, description = this.description,
                date = this.date, isDisclosed = this.isSolved)

        return Crime(currentCrime!!.id, this.title, this.description,
            this.date, this.isSolved)
    }

}