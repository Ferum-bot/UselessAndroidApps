package com.example.rickandmorty.repository

import com.example.rickandmorty.core.mapping.toList
import com.example.rickandmorty.core.mapping.toRickAndMortyCharacter
import com.example.rickandmorty.core.mapping.toRickAndMortyCharacterDB
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.database.dao.RickAndMortyDao
import com.example.rickandmorty.network.api.RickAndMortyApiService

class MainRepository private constructor(
    private val localSource: RickAndMortyDao,
    private val remoteSource: RickAndMortyApiService
) {

    suspend fun getCharactersFromPage(page: Int, policy: CachePolicies): List<RickAndMortyCharacter> {
        return when(policy) {
            CachePolicies.NETWORK -> {
                val result = remoteSource.getCharactersFromPage(page).toList()
                localSource.deleteAllCharacters()
                localSource.insertCharacters(result.map { it.toRickAndMortyCharacterDB() })
                result.map { it.toRickAndMortyCharacter() }
            }
            CachePolicies.LOCAL -> {
                val result = localSource.getAllCharacters()
                result.map { it.toRickAndMortyCharacter() }
            }
        }
    }


}