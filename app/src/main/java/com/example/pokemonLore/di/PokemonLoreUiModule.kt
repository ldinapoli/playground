package com.example.pokemonLore.di

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.pokemonLore.ui.MainActivity
import com.example.pokemonLore.ui.PokemonDetailActivity
import com.example.pokemonLore.ui.PokemonDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [PokemonLoreUiModule.MainActivitySubComponent::class, PokemonLoreUiModule.PokemonDetailActivitySubComponent::class])

abstract class PokemonLoreUiModule {

    // ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    internal abstract fun bindPokemonDetailViewModel(pokemonDetailViewModel: PokemonDetailViewModel): ViewModel

    // Activities

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Subcomponent
    interface MainActivitySubComponent : AndroidInjector<MainActivity> {
        @Subcomponent.Builder
        abstract class Builder : AndroidInjector.Builder<MainActivity>()
    }

    @Binds
    @IntoMap
    @ActivityKey(PokemonDetailActivity::class)
    abstract fun bindPokemonDetailActivityInjectorFactory(builder: PokemonDetailActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Subcomponent
    interface PokemonDetailActivitySubComponent : AndroidInjector<PokemonDetailActivity> {
        @Subcomponent.Builder
        abstract class Builder : AndroidInjector.Builder<PokemonDetailActivity>()
    }
}