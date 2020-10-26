package com.example.pokemonLore.api.client

import com.example.pokemonLore.models.PokemonListResponse
import com.example.pokemonLore.models.PokemonResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonLoreApiService {

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: String): Observable<PokemonResponse>

    @GET("pokemon")
    fun getPokemonList(@Query("offset") offSet: String, @Query("limit") limit: String): Observable<PokemonListResponse>
}