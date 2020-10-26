package com.example.pokemonLore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonLore.databinding.ActivityMainBinding
import com.example.pokemonLore.entities.Pokemon

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startActivity(Intent(this, PokemonDetailActivity::class.java))

        val testAdapter = TestAdapter(emptyList()) { pokemon ->  testAdapterClick(pokemon)}
    }

    private fun testAdapterClick(pokemon: Pokemon) {
        pokemon.name
    }
}
