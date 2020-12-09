package com.example.rickandmorty.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestResultVO(

        @Json(name = "info")
        val infoVO: RickAndMortyInfoVO,

        @Json(name = "results")
        val characterVOList: List<RickAndMortyCharacterVO>

):Parcelable
