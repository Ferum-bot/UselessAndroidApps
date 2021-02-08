package com.github.ferum_bot.core_network.api

import com.github.ferum_bot.core_network.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 22:07
 * Project: Games-RAWG
 */
interface RAWGApi {

    @GET("api/games")
    suspend fun getGames(
        @QueryMap
        parameters: Map<String, String>
    ): PageResponse

}