package com.example.pokemonLore.api

import com.example.pokemonLore.api.client.PokemonLoreApiClient
import com.example.pokemonLore.api.client.PokemonLoreApiService
import com.example.pokemonLore.models.PokemonResponse
import io.reactivex.Single
import javax.inject.Inject

class PokemonLoreApiManager @Inject constructor(pokemonLoreApiClient: PokemonLoreApiClient) {

    private val pokemonLoreApi = pokemonLoreApiClient.buildService(PokemonLoreApiService::class.java)

    fun getPokemonById(id: String): Single<PokemonResponse> = pokemonLoreApi.getPokemonById(id)

}