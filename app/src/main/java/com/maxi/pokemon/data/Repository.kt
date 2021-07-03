package com.maxi.pokemon.data

import android.content.Context
import com.maxi.pokemon.data.remote.BaseApiResponse
import com.maxi.pokemon.data.remote.RemoteDataSource
import com.maxi.pokemon.model.NetworkResult
import com.maxi.pokemon.model.Pokemon
import com.maxi.pokemon.model.PokemonResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @ApplicationContext context: Context
) : BaseApiResponse(context) {

    suspend fun getAllPokemon(): Flow<NetworkResult<PokemonResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getAllPokemon() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPokemonDetails(id: String): Flow<NetworkResult<Pokemon>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getPokemonDetails(id) })
        }.flowOn(Dispatchers.IO)
    }

}