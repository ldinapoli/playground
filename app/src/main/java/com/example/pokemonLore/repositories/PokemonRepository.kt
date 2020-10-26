package com.example.pokemonLore.repositories

import com.example.pokemonLore.api.PokemonLoreApiManager
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.entities.PokemonSprite
import com.example.pokemonLore.models.PokemonListResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonLoreApiManager: PokemonLoreApiManager) {

    fun getPokemonById(id: String): Observable<Pokemon> {
        return pokemonLoreApiManager.getPokemonById(id).map {
            Pokemon(
                it.name,
                PokemonSprite(
                    it.sprites.frontDefault
                )
            )
        }
    }

    fun getPokemonList(offSet: String, limit: String): Observable<PokemonListResponse> {
       return pokemonLoreApiManager.getPokemonList(offSet, limit)
    }
}