package com.example.pokemonLore.repositories

import com.example.pokemonLore.api.PokemonLoreApiManager
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.entities.PokemonSprite
import com.example.pokemonLore.models.PokemonListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.http.Query
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonLoreApiManager: PokemonLoreApiManager) {

    suspend fun getPokemonByIdFlow(id: String): Flow<Pokemon> {
        return pokemonLoreApiManager.getPokemonBydIdFlow(id).map {
            Pokemon(it.name, PokemonSprite(it.sprites.frontDefault))
        }
    }

    suspend fun getPokemonListFlow(offSet: Int, limit: Int): Flow<PokemonListResponse> {
        return pokemonLoreApiManager.getPokemonListFlow(offSet.toString(), limit.toString())
    }
}