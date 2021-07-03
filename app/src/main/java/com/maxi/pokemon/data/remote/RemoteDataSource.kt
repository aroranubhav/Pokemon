package com.maxi.pokemon.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val pokemonService: PokemonService) {

    suspend fun getAllPokemon() =
        pokemonService.getAllPokemon()

    suspend fun getPokemonDetails(id: String) =
        pokemonService.getPokemonDetails(id)

}