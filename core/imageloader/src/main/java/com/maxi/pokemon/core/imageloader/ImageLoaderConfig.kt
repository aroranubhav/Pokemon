package com.maxi.pokemon.core.imageloader

import android.content.Context
import coil.Coil
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache

object ImageLoaderConfig {

    fun initialize(context: Context) {
        Coil.setImageLoader(
            build(context)
        )
    }

    fun build(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(.20)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .maxSizeBytes(100L * 1024 * 1024)
                    .build()
            }
            .respectCacheHeaders(false)
            .crossfade(true)
            .build()
    }
}