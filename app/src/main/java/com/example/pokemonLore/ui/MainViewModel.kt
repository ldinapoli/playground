package com.example.pokemonLore.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.repositories.PokemonRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemonListStateFlow = MutableStateFlow<Response<List<Pokemon>>>(Response.NotInitialized)
    val pokemonListStateFlow: StateFlow<Response<List<Pokemon>>> get() = _pokemonListStateFlow

    fun getPokemonListFlow(offSet: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _pokemonListStateFlow.value = Response.Success(pokemonRepository.getPokemonListFlow(offSet, 5)
                    .onStart { _pokemonListStateFlow.value = Response.Loading }
                    .flatMapConcat { it.results.asFlow() }
                    .flatMapConcat { pokemon -> pokemonRepository.getPokemonByIdFlow(pokemon.name) }
                    .catch { e -> _pokemonListStateFlow.value = Response.Error(e) }
                    .toList())
        }
    }
}