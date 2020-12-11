package com.example.rickandmorty.repository

import com.example.rickandmorty.core.mapping.toList
import com.example.rickandmorty.core.mapping.toRickAndMortyCharacter
import com.example.rickandmorty.core.mapping.toRickAndMortyCharacterDB
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.database.dao.RickAndMortyDao
import com.example.rickandmorty.network.api.RickAndMortyApiService
import com.example.rickandmorty.network.models.RickAndMortyCharacterVO
import com.example.rickandmorty.network.models.RickAndMortyInfoVO
import com.example.rickandmorty.preferences.AppPreferences

class MainRepository(
    private val localSource: RickAndMortyDao,
    private val remoteSource: RickAndMortyApiService,
    private val preferences: AppPreferences
) {

    val numberOfAvailablePages: Int
    get() = preferences.getNumberOfAvailablePages()

    val numberOfPages: Int
    get() = preferences.getNumberOfPages()

    val numberOfCharacters: Int
    get() = preferences.getNumberOfCharacters()

    val numberOfAvailableCharacters: Int
    get() = preferences.getNumberOfAvailableCharacters()

    suspend fun getAllCharactersFromNetwork(page: Int): List<RickAndMortyCharacter> {
        val requestResult = remoteSource.getCharactersFromPage(page)
        val infoVO = requestResult.infoVO
        updatePreferences(infoVO, page, requestResult.characterVOList.size)

        val resultList = requestResult.characterVOList
        updateDatabase(resultList)
        return resultList.map { it.toRickAndMortyCharacter() }
    }

    suspend fun getAllCharactersFromDatabase(): List<RickAndMortyCharacter> {
        val result = localSource.getAllCharacters()
        return result.map { it.toRickAndMortyCharacter() }
    }

    private fun updatePreferences(info: RickAndMortyInfoVO, requirePage: Int, requiredNumberOfCharacters: Int) {
        preferences.updateNumberOfPages(info.pages)
        preferences.updateNumberOfCharacters(info.count)
        if (requirePage > numberOfAvailablePages) {
            preferences.updateNumberOfAvailablePages(requirePage)
            var currentNumberOfAvailableCharacters = preferences.getNumberOfAvailableCharacters()
            currentNumberOfAvailableCharacters += requiredNumberOfCharacters
            preferences.updateNumberOfAvailableCharacters(currentNumberOfAvailableCharacters)
        }
    }

    private suspend fun updateDatabase(result: List<RickAndMortyCharacterVO>) {
        val listToPush = result.map { it.toRickAndMortyCharacterDB() }
        localSource.insertCharacters(listToPush)
    }


}