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
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PokemonDetailViewModel @ViewModelInject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var disposables: CompositeDisposable? = CompositeDisposable()
    private val pokemonMutableLiveData = MutableLiveData<Response<Pokemon>>()
    val pokemonLiveData: LiveData<Response<Pokemon>>
        get() = pokemonMutableLiveData

    private val pokemonListMutableLiveData = MutableLiveData<Response<List<Pokemon>>>()
    val pokemonListLiveData: LiveData<Response<List<Pokemon>>>
    get() = pokemonListMutableLiveData

    fun getPokemonById(id: String) {
        if (id.isEmpty().or(id.count() > 4)) {
            throw IllegalArgumentException("pokemon id must not be null")
        }
        disposables?.add(pokemonRepository.getPokemonById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { pokemonMutableLiveData.value = Response.Loading }
            .subscribe({ success -> pokemonMutableLiveData.value = Response.Success(success) },
                { error -> pokemonMutableLiveData.value = Response.Error(error) }))
    }

    fun getPokemonList() {
        disposables?.add(pokemonRepository.getPokemonList("0", "20")
            .flatMap { Observable.fromIterable(it.results) }
            .flatMap { pokemon -> pokemonRepository.getPokemonById(pokemon.name) }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success -> pokemonListMutableLiveData.value = Response.Success(success) },
                {error -> pokemonListMutableLiveData.value = Response.Error(error) }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables?.dispose()
    }

}