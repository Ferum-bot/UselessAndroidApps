package com.github.ferum_bot.core_network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 22:22
 * Project: Games-RAWG
 */
@Parcelize
data class GameVO(
    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "name")
    val title: String = "Title",

    @Json(name = "background_image")
    val backgroundImageURL: String? = ""
): Parcelable
