package com.example.pokemonLore.api

import com.example.pokemonLore.api.client.PokemonLoreApiClient
import com.example.pokemonLore.api.client.PokemonLoreApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonLoreApiManager @Inject constructor(pokemonLoreApiClient: PokemonLoreApiClient) {

    private val pokemonLoreApi = pokemonLoreApiClient.buildService(PokemonLoreApiService::class.java)

    suspend fun getPokemonListFlow(offSet: String, limit: String) = flow {
        emit(pokemonLoreApi.getPokemonListFlow(offSet, limit))
    }

    suspend fun getPokemonBydIdFlow(id: String) = flow {
        emit(pokemonLoreApi.getPokemonByIdFlow(id))
    }
}