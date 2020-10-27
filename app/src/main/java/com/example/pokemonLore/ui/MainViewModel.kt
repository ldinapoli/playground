package com.example.pokemonLore.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.repositories.PokemonRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {

    private var disposable: Disposable? = null

    private val pokemonListMutableLiveData = MutableLiveData<Response<List<Pokemon>>>()
    val pokemonListLiveData: LiveData<Response<List<Pokemon>>>
        get() = pokemonListMutableLiveData

    fun getPokemonList(offSet: Int) {
        disposable = pokemonRepository.getPokemonList(offSet, 5)
                .flatMap { Observable.fromIterable(it.results) }
                .flatMap { pokemon -> pokemonRepository.getPokemonById(pokemon.name) }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { pokemonListMutableLiveData.value = Response.Loading }
                .subscribe({ success -> pokemonListMutableLiveData.value = Response.Success(success) },
                        { error -> pokemonListMutableLiveData.value = Response.Error(error) })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}