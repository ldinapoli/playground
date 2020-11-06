package com.example.pokemonLore.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonLore.entities.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM Pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllPokemon(): Flow<List<Pokemon>>
}