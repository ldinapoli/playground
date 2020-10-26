package com.example.pokemonLore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.databinding.ActivityPokemonDetailBinding
import com.example.pokemonLore.entities.Pokemon
import dagger.android.AndroidInjection
import javax.inject.Inject

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailBinding

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private val model by lazy { ViewModelProvider(this, viewModelProvider).get(PokemonDetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.getPokemonById("2")
        model.pokemonLiveData.observe(this, Observer { handlePokemonLiveData(it) })
    }

    private fun handlePokemonLiveData(response: Response<Pokemon>) {
        when(response) {
            is Response.Success -> { binding.pokemoName.text = response.data.name }
            is Response.Error -> { binding.pokemoName.text = response.exception.message }
            else -> {}
        }
    }
}
