package com.maxi.pokemon.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maxi.pokemon.data.Repository
import com.maxi.pokemon.model.NetworkResult
import com.maxi.pokemon.model.Pokemon
import com.maxi.pokemon.model.PokemonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _pokemonResponse: MutableLiveData<NetworkResult<PokemonResponse>> = MutableLiveData()
    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val pokemonDetailsList = arrayListOf<Pokemon>()
    val pokemonList = _pokemonList

    val responseType: MutableLiveData<NetworkResult<Pokemon>> = MutableLiveData()

    fun fetchAllPokemonResponse() = viewModelScope.launch {
        repository.getAllPokemon().collect { values ->
            responseType.value = NetworkResult.Loading()
            _pokemonResponse.value = values
            _pokemonResponse.value?.data?.let {
                for (result in it.results) {
                    val id = result.url.substring(34).dropLast(1)
                    repository.getPokemonDetails(id).collect { value ->
                        when (value) {
                            is NetworkResult.Success -> {
                                value.data?.let {
                                    pokemonDetailsList.add(value.data)
                                }
                            }

                            is NetworkResult.Error -> {
                                val errorMessage = value.message ?: "An Error Occurred!!"
                                responseType.value = NetworkResult.Error(errorMessage)
                            }

                            is NetworkResult.Loading -> {
                                responseType.value = NetworkResult.Loading()
                            }
                        }
                    }
                }
            }
            _pokemonList.value = pokemonDetailsList
        }
    }
}