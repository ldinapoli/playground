package com.example.pokemonLore.api.client

import com.example.pokemonLore.models.PokemonListResponse
import com.example.pokemonLore.models.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonLoreApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemonByIdFlow(@Path("id") id: String): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemonListFlow(@Query("offset") offSet: String, @Query("limit") limit: String): PokemonListResponse
}