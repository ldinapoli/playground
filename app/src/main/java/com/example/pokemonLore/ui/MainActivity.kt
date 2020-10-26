package com.example.pokemonLore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonLore.databinding.ActivityMainBinding
import com.example.pokemonLore.entities.Pokemon

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity(Intent(this, PokemonDetailActivity::class.java))

        val testAdapter = TestAdapter(emptyList()) { pokemon ->  testAdapterClick(pokemon)}
    }

    private fun testAdapterClick(pokemon: Pokemon) {
        pokemon.name
    }
}
