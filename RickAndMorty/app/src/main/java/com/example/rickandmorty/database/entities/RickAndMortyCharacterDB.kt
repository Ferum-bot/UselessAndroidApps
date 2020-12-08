package com.example.rickandmorty.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rick_and_morty_character_table")
data class RickAndMortyCharacterDB(

    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,

    @ColumnInfo(name = "gender")
    val gender: String = "male",

    @ColumnInfo(name = "status")
    val status: String = "alive"

) {
}