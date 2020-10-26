package com.example.pokemonLore.api.client

import com.example.pokemonLore.models.PokemonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonLoreApiService {

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: String): Single<PokemonResponse>
}