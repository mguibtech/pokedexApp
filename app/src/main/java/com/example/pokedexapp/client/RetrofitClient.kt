package com.example.pokedexapp.client

import com.example.pokedexapp.services.PokeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun getInstance(): PokeApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PokeApiService::class.java)
        }
    }
}
