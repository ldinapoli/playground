package com.example.pokemonLore.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.repositories.PokemonRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class PokemonDetailViewModel @ViewModelInject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _pokemonMutableStateFlow =
        MutableStateFlow<Response<Pokemon>>(Response.NotInitialized)
    val pokemonStateFlow: StateFlow<Response<Pokemon>> get() = _pokemonMutableStateFlow

    fun getPokemonByIdFlow(id: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            pokemonRepository.getPokemonByIdFlow(id)
                .onStart {
                    _pokemonMutableStateFlow.value = Response.Loading
                }
                .catch { e ->
                    _pokemonMutableStateFlow.value = Response.Error(e)
                }
                .collect { pokemon ->
                    _pokemonMutableStateFlow.value = Response.Success(pokemon)
                }
        }
    }
}