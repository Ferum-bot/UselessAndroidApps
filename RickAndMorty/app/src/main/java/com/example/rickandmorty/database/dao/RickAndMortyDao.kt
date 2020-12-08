package com.example.rickandmorty.database.dao

import androidx.room.*
import com.example.rickandmorty.database.dao.RickAndMortyDao.Companion.TABLE_NAME
import com.example.rickandmorty.database.entities.RickAndMortyCharacterDB

@Dao
interface RickAndMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterDB: RickAndMortyCharacterDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(charactersDB: List<RickAndMortyCharacterDB>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacter(characterDB: RickAndMortyCharacterDB)

    @Delete
    suspend fun deleteCharacter(characterDB: RickAndMortyCharacterDB)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllCharacters()

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id LIMIT 1")
    suspend fun getCharacter(id: Int)

    @Query("SELECT EXISTS (SELECT * FROM $TABLE_NAME WHERE id =:id )")
    suspend fun isCharacterExists(id: Int)

    companion object {

        private const val TABLE_NAME = "rick_and_morty_character_table"

    }
}