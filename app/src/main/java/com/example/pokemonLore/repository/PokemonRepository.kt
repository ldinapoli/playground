package com.example.pokemonLore.repository

import com.example.pokemonLore.api.PokemonLoreApiManager
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.entities.PokemonSprites
import io.reactivex.Single
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonLoreApiManager: PokemonLoreApiManager) {

    fun getPokemonById(id: String): Single<Pokemon> {
        return pokemonLoreApiManager.getPokemonById(id).map {
            Pokemon(
                it.name,
                PokemonSprites(
                    it.sprites.frontDefault
                )
            )
        }
    }
}