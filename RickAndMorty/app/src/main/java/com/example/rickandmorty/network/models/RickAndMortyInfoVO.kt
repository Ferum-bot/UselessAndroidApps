package com.example.rickandmorty.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RickAndMortyInfoVO(

        @Json(name = "count")
        val count: Int,

        @Json(name = "pages")
        val pages: Int,

        @Json(name = "next")
        val nextPageUrl: String

): Parcelable
