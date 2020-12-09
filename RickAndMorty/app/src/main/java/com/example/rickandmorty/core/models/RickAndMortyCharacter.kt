package com.example.rickandmorty.core.models

data class RickAndMortyCharacter(
    val id: Int = 0,
    val name: String = "Rick",
    val status: String = "alive",
    val gender: String = "male",
    val imageUrl: String = "",
)
