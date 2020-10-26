package com.example.pokemonLore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonLore.R
import com.example.pokemonLore.entities.Pokemon


class TestAdapter(private val pokemonList: List<Pokemon>, private val clickListener: (Pokemon) -> Unit): RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.activity_pokemon_detail,
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
        }
    }
}