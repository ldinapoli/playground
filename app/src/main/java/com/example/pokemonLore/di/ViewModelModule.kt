package com.example.pokemonLore.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindPokemonViewModelFactory(factory: PokemonViewModelFactory): ViewModelProvider.Factory
}