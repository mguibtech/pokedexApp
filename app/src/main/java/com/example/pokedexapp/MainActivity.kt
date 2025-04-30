package com.example.pokedexapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.client.RetrofitClient
import com.example.pokedexapp.models.Pokemon
import com.example.pokedexapp.ui.theme.PokedexAppTheme
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PokedexAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        Greeting(name = "Android")

                        Spacer(modifier = Modifier.height(16.dp))

                        ElevatedButton(
                            onClick = { fetchPokemon("ditto") },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Buscar Pokémon")
                        }
                    }
                }
            }
        }
    }

    private fun fetchPokemon(name: String) {
        val service = RetrofitClient.getInstance()
        val call = service.getPokemon(name)

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    Log.d("PokeAPI", "Pokemon: ${pokemon?.name}, Altura: ${pokemon?.height}")

                    // Enviar para outra Activity
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("pokemon_json", Gson().toJson(pokemon))
                    startActivity(intent)
                } else {
                    Log.e("PokeAPI", "Erro: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.e("PokeAPI", "Falha: ${t.message}")
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexAppTheme {
        Greeting("Android")
    }
}
