package com.example.pokemonLore.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(val name: String, val sprite: PokemonSprite) : Parcelable