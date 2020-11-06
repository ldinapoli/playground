package com.example.pokemonLore.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Pokemon(@PrimaryKey val name: String, val sprite: PokemonSprite) : Parcelable