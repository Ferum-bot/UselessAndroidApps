package com.example.rickandmorty.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RickAndMortyCharacterVO(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "status")
    val status: String,

    @Json(name  = "gender")
    val gender: String,

    @Json(name = "image")
    val imageUrl: String

): Parcelable