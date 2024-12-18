package com.example.evaluation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluation.R
import com.example.evaluation.model.InfosViewModel

@Composable
fun HomePage(viewModel: InfosViewModel, navController: NavController) {
    val pokemonList = viewModel.pokemonList.collectAsState()
    val error = viewModel.error.collectAsState()
    val isLoading = pokemonList.value.isEmpty()
    val isDataLoaded = pokemonList.value.isNotEmpty()

    LaunchedEffect(isDataLoaded) {
        if (!isDataLoaded) {
            // Reload les Pokémon si les données ne sont pas déjà chargés
            viewModel.getRandomPokemons()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.pokemon_list),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                actions = {
                    // Bouton pour recharger manuellement les pokemon
                    IconButton(
                        onClick = { viewModel.getRandomPokemons() }
                    ) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                when {
                    isLoading -> CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )

                    error.value != null -> Text(
                        text = "Erreur",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    else -> LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(pokemonList.value) { pokemon ->
                            PokemonItem(pokemon, navController)
                        }
                    }
                }
            }
        }
    )
}