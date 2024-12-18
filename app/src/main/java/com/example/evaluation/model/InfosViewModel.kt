package com.example.evaluation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluation.api.ApiService
import com.example.evaluation.api.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random
import java.util.*

class InfosViewModel : ViewModel() {

    // Initialisation du client Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList
    val pokemonDetails = MutableStateFlow<Pokemon?>(null)
    val error = MutableStateFlow<String?>(null)

    private val _currentLanguage = MutableStateFlow(Locale.getDefault().language) // (déterminée par la langue du système)
    val currentLanguage: StateFlow<String> = _currentLanguage

    // Récupère 10 Pokémon random
    fun getRandomPokemons() {
        val randomPokemons = mutableListOf<Pokemon>()
        val pokemonIds = List(10) { Random.nextInt(1, 1000) }

        viewModelScope.launch {
            try {
                pokemonIds.forEach { id ->
                    val pokemon = fetchPokemonTranslated(id)
                    randomPokemons.add(pokemon)
                }
                _pokemonList.value = randomPokemons
            } catch (e: Exception) {
                error.value = "Erreur lors du chargement des Pokémon : ${e.message}"
            }
        }
    }

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch {
            try {
                val response = fetchPokemonTranslated(id)
                pokemonDetails.value = response
            } catch (e: Exception) {
                error.value = "Erreur de récupération du Pokémon avec ID $id : ${e.message}"
            }
        }
    }

    // Récupère un Pokémon traduit dans la langue actuelle
    private suspend fun fetchPokemonTranslated(id: Int): Pokemon {
        return try {
            val pokemon = service.getPokemonInfo(id)
            val species = service.getPokemonSpecies(id)
            val translatedName = species.names.find { it.language.name == _currentLanguage.value }?.name
                ?: pokemon.name // Si traduction non disponible, utilise le nom original

            // Retourne le Pokémon avec le nom traduit
            pokemon.copy(name = translatedName)
        } catch (e: Exception) {
            throw Exception("Erreur lors de la traduction du Pokémon avec ID $id : ${e.message}")
        }
    }
}
