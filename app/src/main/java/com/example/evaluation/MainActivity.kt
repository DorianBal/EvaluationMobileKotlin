package com.example.evaluation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.evaluation.model.InfosViewModel
import com.example.evaluation.ui.theme.EvaluationTheme
import com.example.evaluation.composables.*

class MainActivity : ComponentActivity() {
    private val pokeInfoViewModel: InfosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvaluationTheme {
                // Met en place la navigation
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pokemonList") {
                    composable("pokemonList") {
                        HomePage(viewModel = pokeInfoViewModel, navController = navController)
                    }
                    composable(
                        "pokemonDetail/{pokemonId}",
                        arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
                        if (pokemonId != null) {
                            PokemonDetailScreen(
                                pokemonId = pokemonId,
                                viewModel = pokeInfoViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}