package com.example.rickandmorty.core.mapping

import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.database.entities.RickAndMortyCharacterDB
import com.example.rickandmorty.network.models.RequestResultVO
import com.example.rickandmorty.network.models.RickAndMortyCharacterVO


fun RequestResultVO.toList(): List<RickAndMortyCharacterVO> {
    return this.characterVOList
}

fun RickAndMortyCharacterVO.toRickAndMortyCharacterDB(): RickAndMortyCharacterDB {
    return RickAndMortyCharacterDB(
            id,
            name,
            status,
            gender,
            imageUrl
    )
}

fun RickAndMortyCharacterDB.toRickAndMortyCharacterVO(): RickAndMortyCharacterVO {
    return RickAndMortyCharacterVO(
            id,
            name,
            status,
            gender,
            imageUrl
    )
}

fun RickAndMortyCharacterVO.toRickAndMortyCharacter(): RickAndMortyCharacter {
    return RickAndMortyCharacter(
            id,
            name,
            status,
            gender,
            imageUrl
    )
}

fun RickAndMortyCharacterDB.toRickAndMortyCharacter(): RickAndMortyCharacter {
    return RickAndMortyCharacter(
            id,
            name,
            status,
            gender,
            imageUrl
    )
}