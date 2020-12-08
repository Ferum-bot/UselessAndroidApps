package com.example.rickandmorty.network.models

import com.squareup.moshi.Json

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

) {
}