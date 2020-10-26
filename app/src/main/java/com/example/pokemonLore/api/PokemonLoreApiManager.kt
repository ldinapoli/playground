package com.example.pokemonLore.api

import com.example.pokemonLore.api.client.PokemonLoreApiClient
import com.example.pokemonLore.api.client.PokemonLoreApiService
import com.example.pokemonLore.models.PokemonListResponse
import com.example.pokemonLore.models.PokemonResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PokemonLoreApiManager @Inject constructor(pokemonLoreApiClient: PokemonLoreApiClient) {

    private val pokemonLoreApi = pokemonLoreApiClient.buildService(PokemonLoreApiService::class.java)

    fun getPokemonById(id: String): Observable<PokemonResponse> = pokemonLoreApi.getPokemonById(id)

    fun getPokemonList(offSet: String, limit: String): Observable<PokemonListResponse> = pokemonLoreApi.getPokemonList(offSet, limit)

}