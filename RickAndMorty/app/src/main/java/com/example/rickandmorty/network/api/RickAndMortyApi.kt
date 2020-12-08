package com.example.rickandmorty.network.api

import com.example.rickandmorty.network.models.RickAndMortyCharacterVO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://rickandmortyapi.com/api"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getAllCharactersFromPage(
        @Query("pages")
        page: Int
    ): List<RickAndMortyCharacterVO>

    @GET("character")
    suspend fun getCharacter(
        @Query("id")
        id: Int = 0
    ): RickAndMortyCharacterVO

}


object RickAndMortyApi {
    val RickAndMortyService: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}