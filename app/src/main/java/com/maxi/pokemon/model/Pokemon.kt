package com.maxi.pokemon.model


import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites
)