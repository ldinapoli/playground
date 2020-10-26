package com.example.pokemonLore.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {

    private var disposable: Disposable? = null
    var pokemonLiveData = MutableLiveData< Response<Pokemon>>()

    fun getPokemonById(id: String) {
        disposable = pokemonRepository.getPokemonById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { pokemonLiveData.value = Response.Loading }
            .subscribe( { success -> pokemonLiveData.value = Response.Success(success) },
                { error -> pokemonLiveData.value = Response.Error(error) } )

    }
}