package com.example.rickandmorty.network.api

import com.example.rickandmorty.network.models.RequestResultVO
import com.example.rickandmorty.network.models.RickAndMortyCharacterVO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://rickandmortyapi.com/api/"

private const val CHARACTER = "character"
private const val LOCATION = "location"
private const val EPISODE = "episode"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharactersFromPage(
            @Query("page")
            page: Int
    ): RequestResultVO

    @GET("character")
    suspend fun getCharactersByName(
            @Query("name")
            name: String
    ): RequestResultVO

    @GET("character")
    suspend fun getCharactersByStatus(
            @Query("status")
            status: String
    ): RequestResultVO

    @GET("character")
    suspend fun getCharacterByGender(
            @Query("gender")
            gender: String
    )

}




object RickAndMortyApi {
    val RickAndMortyService: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}