package com.example.pokemonLore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.databinding.ActivityMainBinding
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.models.PokemonListResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var currentListSelected: Int = 0

    private val model: MainViewModel by viewModels()

    companion object {
        private const val OFF_SET = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.pokemonListLiveData.observe(this) { handlePokemonListResponse(it) }
        model.getPokemonList(0)
        init()
    }

    private fun handlePokemonListResponse(response: Response<List<Pokemon>>) {
        when(response) {
            is Response.Success -> { setupResponse(response.data) }
            is Response.Error -> {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
            is Response.Loading -> {
                toggleLoading()
            }
        }
    }

    private fun setupResponse(pokemonList: List<Pokemon>) {
        toggleLoading()
        binding.mainRecycler.isVisible = true
        binding.mainButtonDown.isEnabled = currentListSelected > 0
        binding.mainRecycler.layoutManager = LinearLayoutManager(this)
        binding.mainRecycler.adapter = MainPokemonListAdapter(pokemonList) { pokemon -> pokemonListAdapterClick(pokemon) }
    }

    private fun toggleLoading() {
        binding.mainProgressBar.isGone = !binding.mainProgressBar.isGone
    }

    private fun pokemonListAdapterClick(pokemon: Pokemon) {
        startActivity(Intent(this, PokemonDetailActivity::class.java).apply {
            putExtra(PokemonDetailActivity.POKEMON_EXTRA, pokemon)
        })
    }

    private fun init() {
        binding.mainButtonUp.setOnClickListener {
            currentListSelected += OFF_SET
            model.getPokemonList(currentListSelected)
            binding.mainRecycler.isVisible = false
        }
        binding.mainButtonDown.setOnClickListener {
            if (currentListSelected > 0) {
                currentListSelected -= OFF_SET
                model.getPokemonList(currentListSelected)
                binding.mainRecycler.isVisible = false
            }
        }
    }
}
