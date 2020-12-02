package com.example.criminalintent.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.criminalintent.database.MainDatabase
import com.example.criminalintent.database.entities.Crime
import kotlinx.coroutines.*

class AppRepository private constructor(context: Context) {

    private val database = MainDatabase.getInstance(context)

    private val crimeDao = database.crimeDatabaseDao

    private val repositoryJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + repositoryJob)

    val allCrimes = crimeDao.getAllCrimes()

    companion object {

        private const val DATABASE_NAME = "main_database"

        private var INSTANCE: AppRepository? = null

        fun getInstance(context: Context): AppRepository {
            if (INSTANCE == null) {
                INSTANCE = AppRepository(context)
            }
            return INSTANCE!!
        }

    }

    fun getCrime(id: Long) {
        uiScope.launch {
            getCrimeFromDataBase(id)
        }
    }

    fun clearAllCrimes() {
        uiScope.launch {
            deleteAllCrimesFromDatabase()
        }
    }

    fun deleteCrime(crime: Crime) {
        uiScope.launch {
            deleteCrimeFromDatabase(crime)
        }
    }

    fun updateCrime(crime: Crime) {
        uiScope.launch {
            updateCrimeFromDatabase(crime)
        }
    }

    fun insertCrime(crime: Crime) {
        uiScope.launch {
            insertCrimeToDatabase(crime)
        }
    }

    private suspend fun getCrimeFromDataBase(id: Long): Crime {
        return withContext(Dispatchers.IO) {
            crimeDao.getCrime(id)
        }
    }

    private suspend fun deleteAllCrimesFromDatabase() {
        withContext(Dispatchers.IO) {
            crimeDao.deleteAllCrimes()
        }
    }

    private suspend fun deleteCrimeFromDatabase(crime: Crime) {
        withContext(Dispatchers.IO) {
            crimeDao.deleteCrime(crime.id)
        }
    }

    private suspend fun insertCrimeToDatabase(crime: Crime) {
        withContext(Dispatchers.IO) {
            crimeDao.insert(crime)
        }
    }

    private suspend fun updateCrimeFromDatabase(crime: Crime) {
        withContext(Dispatchers.IO) {
            crimeDao.update(crime)
        }
    }

}