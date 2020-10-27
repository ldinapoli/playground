package com.example.pokemonLore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonLore.R
import com.example.pokemonLore.entities.Pokemon
import kotlinx.android.synthetic.main.main_pokemon_list_adapter.view.*

class MainPokemonListAdapter(private val pokemonList: List<Pokemon>, private val clickListener: (Pokemon) -> Unit): RecyclerView.Adapter<MainPokemonListAdapter.TestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.main_pokemon_list_adapter,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(pokemonList[position], clickListener)
    }

    inner class TestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon, clickListener: (Pokemon) -> Unit) {
            itemView.setOnClickListener { clickListener(pokemon) }
            Glide.with(itemView.context)
                    .load(pokemon.sprite.frontDefault)
                    .into(itemView.pokemon_list_adapter_image)
            itemView.pokemon_list_adapter_name.text = pokemon.name
        }
    }
}