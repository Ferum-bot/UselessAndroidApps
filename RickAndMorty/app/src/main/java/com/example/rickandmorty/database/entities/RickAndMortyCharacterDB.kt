package com.example.rickandmorty.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "rick_and_morty_character_table")
data class RickAndMortyCharacterDB(

    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "status")
    val status: String = "alive",

    @ColumnInfo(name = "gender")
    val gender: String = "male",

    @ColumnInfo(name = "image_url")
    val imageUrl: String = "",

): Parcelable