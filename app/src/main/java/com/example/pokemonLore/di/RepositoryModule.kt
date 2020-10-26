package com.example.pokemonLore.di

import com.example.pokemonLore.api.PokemonLoreApiManager
import com.example.pokemonLore.api.client.PokemonLoreApiClient
import com.example.pokemonLore.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun pokemonRepository(pokemonLoreApiManager: PokemonLoreApiManager): PokemonRepository = PokemonRepository(pokemonLoreApiManager)

    @Provides
    fun leagueLoreApiManager(pokemonLoreApiClient: PokemonLoreApiClient): PokemonLoreApiManager = PokemonLoreApiManager(pokemonLoreApiClient)
}