package com.example.pokemonLore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemonLore.databinding.ActivityMainBinding
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.repository.PokemonRepository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: PokemonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
