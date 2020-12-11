package com.example.rickandmorty.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences private constructor(context: Context) {

    private val storage: SharedPreferences

    init {
        storage = context.getSharedPreferences(NAME_OF_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }


    fun updateNumberOfPages(numberOfPages: Int) {
        val editor = storage.edit()
        editor.putInt(NUMBER_OF_PAGE_NAME, numberOfPages)
        editor.apply()
    }

    fun getNumberOfPages(): Int {
        return storage.getInt(NUMBER_OF_PAGE_NAME, 0)
    }

    fun updateNumberOfCharacters(numberOfCharacters: Int) {
        val editor = storage.edit()
        editor.putInt(NUMBER_OF_CHARACTERS_NAME, numberOfCharacters)
        editor.apply()
    }

    fun updateNumberOfAvailablePages(numberOfAvailablePages: Int) {
        val editor = storage.edit()
        editor.putInt(NUMBER_OF_AVAILABLE_PAGE_NAME, numberOfAvailablePages)
        editor.apply()
    }

    fun getNumberOfAvailablePages(): Int {
        return storage.getInt(NUMBER_OF_AVAILABLE_PAGE_NAME, 0)
    }

    fun getNumberOfCharacters(): Int {
        return storage.getInt(NUMBER_OF_CHARACTERS_NAME, 0)
    }

    fun getNumberOfAvailableCharacters(): Int {
        return storage.getInt(NUMBER_OF_AVAILABLE_CHARACTERS_NAME, 0)
    }

    fun updateNumberOfAvailableCharacters(numberOfAvailableCharacters: Int) {
        val editor = storage.edit()
        editor.putInt(NUMBER_OF_AVAILABLE_CHARACTERS_NAME, numberOfAvailableCharacters)
        editor.apply()
    }

    companion object {

        private var INSTANCE: AppPreferences? = null

        private const val NAME_OF_SHARED_PREFERENCES = "RickAndMortyInfo"
        private const val NUMBER_OF_PAGE_NAME = "NumberOfPages"
        private const val NUMBER_OF_AVAILABLE_PAGE_NAME = "NumberOfAvailablePages"
        private const val NUMBER_OF_AVAILABLE_CHARACTERS_NAME = "NumberOfAvailableCharacters"
        private const val NUMBER_OF_CHARACTERS_NAME = "NumberOfCharacters"

        fun getAppPreferences(context: Context): AppPreferences {
            var instance = INSTANCE
            if (instance == null) {
                instance = AppPreferences(context)
                INSTANCE = instance
            }
            return instance
        }

    }

}