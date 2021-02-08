package com.github.ferum_bot.core_network.api.pagging

import androidx.paging.PagingSource
import com.github.ferum_bot.core_network.api.RAWGApi
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.core_network.model.GameVO
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 23:17
 * Project: Games-RAWG
 */
class GamesPagingSource @Inject constructor(
    private val service: RAWGApi,
    private val parameters: GamesApiParameters
): PagingSource<Int, GameVO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameVO> {
        val position = params.key ?: GAMES_API_STARTER_PAGE

        return try {
            val response = service.getGames(parameters.applyPagingParametersAndConvertToMap(position))
            val gameList = response.resultListOfGames
            LoadResult.Page(
                data = gameList,
                prevKey = if (response.previousPage == null) {
                    null
                }
                else {
                    position - 1
                },
                nextKey = if (response.nextPage == null) {
                    null
                }
                else {
                    position + 1
                }
            )
        }
        catch (ex: IOException) {
            LoadResult.Error(ex)
        }
        catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

    private fun GamesApiParameters.applyPagingParametersAndConvertToMap(page: Int = 1): Map<String, String>
        = convertToMap().toMutableMap().apply {
            put("page", page.toString())
            put("page_size", DEFAULT_PAGE_SIZE.toString())
    }

    companion object {
        private const val GAMES_API_STARTER_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 20
    }
}