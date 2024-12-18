package com.example.evaluation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.evaluation.R
import com.example.evaluation.model.InfosViewModel

@Composable
fun PokemonDetailScreen(pokemonId: Int, viewModel: InfosViewModel) {
    val pokemon = viewModel.pokemonDetails.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val isLoading = pokemon == null

    LaunchedEffect(pokemonId) {
        viewModel.getPokemonInfo(pokemonId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.pokemon_detail),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }

                    error != null -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Text(
                                text = "error",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    else -> {
                        pokemon?.let {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = it.name.capitalize(),
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                it.sprites.frontDefault?.let { imageUrl ->
                                    Image(
                                        painter = rememberImagePainter(imageUrl),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(200.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(id = R.string.types) + ": ${it.types.joinToString { type -> type.type.name.capitalize() }}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = stringResource(id = R.string.hp) + ": ${it.stats.find { stat -> stat.stat.name == "hp" }?.baseStat ?: "N/A"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = stringResource(id = R.string.attack) + ": ${it.stats.find { stat -> stat.stat.name == "attack" }?.baseStat ?: "N/A"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = stringResource(id = R.string.defense) + ": ${it.stats.find { stat -> stat.stat.name == "defense" }?.baseStat ?: "N/A"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = stringResource(id = R.string.weight) + ": ${it.weight / 10.0} kg",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = stringResource(id = R.string.height) + ": ${it.height / 10.0} m",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
