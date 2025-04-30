package com.example.pokedexapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.models.Pokemon
import com.example.pokedexapp.ui.theme.PokedexAppTheme
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonJson = intent.getStringExtra("pokemon_json")
        val pokemon = Gson().fromJson(pokemonJson, Pokemon::class.java)

        setContent {
            PokedexAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PokemonDetails(pokemon = pokemon)
                }
            }
        }
    }
}

@Composable
fun PokemonDetails(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(text = "Nome: ${pokemon.name}", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Altura: ${pokemon.height}")
        Text(text = "Peso: ${pokemon.weight}")
    }
}
