package com.example.pokemonLore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonLore.R
import com.example.pokemonLore.databinding.MainPokemonListAdapterBinding
import com.example.pokemonLore.entities.Pokemon
import kotlinx.android.synthetic.main.main_pokemon_list_adapter.view.*

class MainPokemonListAdapter(private val pokemonList: List<Pokemon>, private val clickListener: (Pokemon) -> Unit): RecyclerView.Adapter<MainPokemonListAdapter.TestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(MainPokemonListAdapterBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(pokemonList[position], clickListener)
    }

    inner class TestViewHolder(private val pokemonListAdapterBinding: MainPokemonListAdapterBinding): RecyclerView.ViewHolder(pokemonListAdapterBinding.root) {
        fun bind(pokemon: Pokemon, clickListener: (Pokemon) -> Unit) {
            pokemonListAdapterBinding.apply {
                Glide.with(itemView.context)
                        .load(pokemon.sprite.frontDefault)
                        .into(pokemonListAdapterImage)

                pokemonListAdapterName.text = pokemon.name
            }

            itemView.setOnClickListener { clickListener(pokemon) }
        }
    }
}