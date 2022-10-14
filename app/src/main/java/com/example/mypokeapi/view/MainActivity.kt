package com.example.mypokeapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokeapi.R
import com.example.mypokeapi.api.PokemonRepository
import com.example.mypokeapi.domain.Pokemon
import com.example.mypokeapi.domain.PokemonType
import com.example.mypokeapi.viewModel.PokemonViewModel
import com.example.mypokeapi.viewModel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.rvPokemons)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}

