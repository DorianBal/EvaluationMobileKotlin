package com.example.evaluation.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Récupère les informations de base d'un Pokémon
    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): Pokemon

    // Récupère les informations de spécification du Pokémon pour la traduction
    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpecies
}

data class PokemonSpecies(
    @SerializedName("names") val names: List<PokemonName>
)

data class PokemonName(
    @SerializedName("name") val name: String,
    @SerializedName("language") val language: Language
)

data class Language(
    @SerializedName("name") val name: String
)
