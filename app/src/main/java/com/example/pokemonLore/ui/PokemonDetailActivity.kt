package com.example.pokemonLore.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonLore.R
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.databinding.ActivityPokemonDetailBinding
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        const val POKEMON_EXTRA = "pokemon_extra"
    }

    private lateinit var binding: ActivityPokemonDetailBinding

    private val model: PokemonDetailViewModel by viewModels()

    private val pokemon by lazy { getPokemonExtra(intent) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.pokemonDetailToolbar)

        title = "Pokemon Detail"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        model.pokemonLiveData.observe(this) { handlePokemonLiveData(it) }

        binding.pokemonDetailAnimation.setAnimation(R.raw.splash)

        model.getPokemonById(pokemon.name)

        setup()
    }

    private fun handlePokemonLiveData(response: Response<Pokemon>) {
        when (response) {
            is Response.Success -> {
                setupPokemonData(response.data)
            }
            is Response.Error -> {
                binding.pokemonDetailName.text = response.exception.message
            }
            else -> {
            }
        }
    }

    private fun setupPokemonData(pokemon: Pokemon) {
        binding.pokemonDetailName.text = pokemon.name
        Glide.with(this).load(pokemon.sprite.frontDefault)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(
                        this@PokemonDetailActivity,
                        "ups hubo un error",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.pokemonDetailAnimation.isVisible = false
                    Log.e("Glide error", e.toString())
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pokemonDetailAnimation.isVisible = false
                    binding.pokemonDetailImage.isInvisible = false
                    return false
                }

            })
            .into(binding.pokemonDetailImage)
    }

    private fun getPokemonExtra(intent: Intent): Pokemon {
        return intent.getParcelableExtra(POKEMON_EXTRA)
    }

    private fun setup() {
        binding.pokemonDetailButton.setOnClickListener {
            try {
                model.getPokemonById(binding.pokemonDetailTextInput.text.toString())
                binding.pokemonDetailAnimation.isVisible = true
                binding.pokemonDetailImage.isInvisible = true
                hideKeyboard()
            } catch(e: IllegalArgumentException) {
                Log.e(this.toString(), "illegal argument")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
