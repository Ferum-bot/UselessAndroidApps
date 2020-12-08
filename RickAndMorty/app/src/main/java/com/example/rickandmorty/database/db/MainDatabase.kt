package com.example.rickandmorty.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.database.dao.RickAndMortyDao
import com.example.rickandmorty.database.db.MainDatabase.Companion.DATABASE_NAME
import com.example.rickandmorty.database.db.MainDatabase.Companion.DATABASE_VERSION
import com.example.rickandmorty.database.entities.RickAndMortyCharacterDB

@Database(
    entities = [
        RickAndMortyCharacterDB::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class MainDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MAIN_DATABASE.bd"

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }

    abstract val rickAndMortyDao: RickAndMortyDao

}