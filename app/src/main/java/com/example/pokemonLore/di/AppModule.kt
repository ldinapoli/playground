package com.example.pokemonLore.di

import com.example.pokemonLore.api.client.PokemonLoreApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun pokemonLoreApiClient(): PokemonLoreApiClient = PokemonLoreApiClient("https://pokeapi.co/api/v2/")
}