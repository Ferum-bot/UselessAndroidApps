package com.example.rickandmorty.core

import com.example.rickandmorty.core.models.RickAndMortyCharacter

fun MutableList<RickAndMortyCharacter>.add(list: List<RickAndMortyCharacter>) {
    val size = list.size
    for (i in 0 until size) {
        this.add(list[i])
    }
}