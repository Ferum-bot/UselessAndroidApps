package com.example.criminalintent.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.criminalintent.database.entities.Crime


@Dao
interface CrimeDao {

    @Insert
    fun insert(crime: Crime)

    @Update
    fun update(crime: Crime)

    @Query("SELECT * FROM CRIME_LIST_TABLE ORDER BY id DESC")
    fun getAllCrimes(): LiveData<List<Crime>>

    @Query("SELECT * FROM CRIME_LIST_TABLE WHERE id = :queryId")
    fun getCrime(queryId: Long): Crime

    @Query("DELETE FROM CRIME_LIST_TABLE")
    fun deleteAllCrimes()

    @Query("DELETE FROM CRIME_LIST_TABLE WHERE id = :queryId")
    fun deleteCrime(queryId: Long)

}