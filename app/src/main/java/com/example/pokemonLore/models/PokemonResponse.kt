package com.example.pokemonLore.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonSpriteResponse
)

data class PokemonSpriteResponse(
    @SerializedName("front_default") val frontDefault: String
)