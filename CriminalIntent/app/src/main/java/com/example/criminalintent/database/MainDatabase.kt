package com.example.criminalintent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.criminalintent.database.converters.CrimeTypeConverters
import com.example.criminalintent.database.dao.CrimeDao
import com.example.criminalintent.database.entities.Crime

@Database(entities = [Crime::class],
        version = 1, exportSchema = false)
@TypeConverters(CrimeTypeConverters::class)
abstract class MainDatabase: RoomDatabase() {

    abstract val crimeDatabaseDao: CrimeDao

    companion object {

        @Volatile // чтобы потоки нормально работали с базой данных
        private var INSTANCE: MainDatabase? = null


        fun getInstance(context: Context): MainDatabase {
            synchronized (this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        MainDatabase::class.java,
                        "main_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}