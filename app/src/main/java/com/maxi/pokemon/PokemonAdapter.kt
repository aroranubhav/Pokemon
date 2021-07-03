package com.maxi.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.maxi.pokemon.model.Pokemon
import com.maxi.pokemon.model.PokemonResponse
import kotlinx.android.synthetic.main.row_pokemon.view.*

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonDiffUtil()) {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.itemView.imgPokemon.load(
            pokemon.sprites.other.officialArtwork.frontDefault
        )
        holder.itemView.txtPokemon.text = pokemon.name
    }

    class PokemonDiffUtil() : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }
}