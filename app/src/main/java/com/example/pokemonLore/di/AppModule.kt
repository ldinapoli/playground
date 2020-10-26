package com.example.pokemonLore.di

import com.example.pokemonLore.api.PokemonLoreApiManager
import com.example.pokemonLore.api.client.PokemonLoreApiClient
import com.example.pokemonLore.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun pokemonRepository(pokemonLoreApiManager: PokemonLoreApiManager): PokemonRepository = PokemonRepository(pokemonLoreApiManager)

    @Provides
    @Singleton
    fun leagueLoreApiManager(pokemonLoreApiClient: PokemonLoreApiClient): PokemonLoreApiManager = PokemonLoreApiManager(pokemonLoreApiClient)

    @Provides
    @Singleton
    fun leagueLoreApiClient(): PokemonLoreApiClient = PokemonLoreApiClient()
}