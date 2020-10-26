package com.example.pokemonLore.di

import com.example.pokemonLore.PokemonLoreApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        PokemonLoreUiModule::class]
)
interface AppComponent {
    fun inject(pokemonLoreApplication: PokemonLoreApplication)
}