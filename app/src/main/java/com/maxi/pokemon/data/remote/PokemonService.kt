package com.maxi.pokemon.data.remote

import com.maxi.pokemon.model.Pokemon
import com.maxi.pokemon.model.PokemonResponse
import com.maxi.pokemon.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET(Constants.ALL_POKEMON_URL)
    suspend fun getAllPokemon(): Response<PokemonResponse>

    @GET(Constants.POKEMON_URL)
    suspend fun getPokemonDetails(@Path("id") id: String): Response<Pokemon>

}