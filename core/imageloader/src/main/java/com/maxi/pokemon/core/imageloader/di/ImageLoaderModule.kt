package com.maxi.pokemon.core.imageloader.di

import android.content.Context
import coil.ImageLoader
import coil.imageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule {

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context
    ): ImageLoader {
        // context.imageLoader reads whatever was set via Coil.setImageLoader()
        // AppStartup has already run by this point, so this is our configured instance
        return context.imageLoader
    }
}