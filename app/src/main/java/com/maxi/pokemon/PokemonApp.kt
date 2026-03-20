package com.maxi.pokemon

import android.app.Application
import com.maxi.pokemon.core.imageloader.ImageLoaderConfig

class PokemonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ImageLoaderConfig.initialize(this@PokemonApp)
    }
}