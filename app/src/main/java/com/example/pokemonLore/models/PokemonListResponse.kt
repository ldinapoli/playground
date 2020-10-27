package com.example.pokemonLore.models

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") val count: String,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<PokemonFromListResponse>
)

data class PokemonFromListResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)