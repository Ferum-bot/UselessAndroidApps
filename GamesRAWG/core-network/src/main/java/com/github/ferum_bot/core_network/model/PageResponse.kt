package com.github.ferum_bot.core_network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 22:14
 * Project: Games-RAWG
 */
@Parcelize
data class PageResponse(
    @Json(name = "count")
    val count: Int = 0,

    @Json(name = "next")
    val nextPage: String? = null,

    @Json(name = "previous")
    val previousPage: String? = null,

    @Json(name = "results")
    val resultListOfGames: List<GameVO> = listOf()
): Parcelable
