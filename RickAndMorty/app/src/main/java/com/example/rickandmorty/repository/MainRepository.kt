package com.example.rickandmorty.repository

import com.example.rickandmorty.core.mapping.toList
import com.example.rickandmorty.core.mapping.toRickAndMortyCharacter
import com.example.rickandmorty.core.mapping.toRickAndMortyCharacterDB
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.database.dao.RickAndMortyDao
import com.example.rickandmorty.network.api.RickAndMortyApiService
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

    suspend fun getCharactersFromPage(page: Int, policy: CachePolicies): List<RickAndMortyCharacter> {
        return when(policy) {
            CachePolicies.NETWORK -> {
                val requestResult = remoteSource.getCharactersFromPage(page)
                val result = requestResult.toList()
                updatePreferences(requestResult.infoVO, page)

//                localSource.deleteAllCharacters()
                localSource.insertCharacters(result.map { it.toRickAndMortyCharacterDB() })
                result.map { it.toRickAndMortyCharacter() }
            }
            CachePolicies.LOCAL -> {
                val result = localSource.getAllCharacters()
                result.map { it.toRickAndMortyCharacter() }
            }
        }
    }


    private fun updatePreferences(info: RickAndMortyInfoVO, requirePage: Int) {
        preferences.updateNumberOfPages(info.pages)
        preferences.updateNumberOfCharacters(info.count)
        if (requirePage > numberOfAvailablePages) {
            preferences.updateNumberOfAvailablePages(requirePage)
        }
    }


}