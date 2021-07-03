package com.maxi.pokemon

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxi.pokemon.databinding.ActivityMainBinding
import com.maxi.pokemon.model.NetworkResult
import com.maxi.pokemon.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private val TAG = MainActivity::class.java.simpleName

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding
    private val pokemonAdapter by lazy {
        PokemonAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initAdapter()
        fetchAllPokemonResponse()
    }

    private fun initAdapter() {
        _binding.rvPokemon.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun fetchAllPokemonResponse() {
        mainViewModel.fetchAllPokemonResponse()
        mainViewModel.responseType.observe(this) { responseType ->
            when (responseType) {
                is NetworkResult.Error -> {
                    _binding.pbPokemon.visibility = View.GONE
                    Toast.makeText(
                        this,
                        responseType.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                is NetworkResult.Loading -> {
                    _binding.pbPokemon.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "fetchAllPokemonResponse: Success !")
                }
            }
        }
        mainViewModel.pokemonList.observe(this) {
            _binding.pbPokemon.visibility = View.GONE
            pokemonAdapter.submitList(it)
        }
    }
}