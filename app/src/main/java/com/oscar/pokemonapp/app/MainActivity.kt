package com.oscar.pokemonapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oscar.pokemonapp.app.ui.theme.PokemonAppTheme
import com.oscar.pokemonapp.commons.NavigationScreen
import com.oscar.pokemonapp.presentation.presentation_allpokes.detail.DetailPokeScreen
import com.oscar.pokemonapp.presentation.presentation_allpokes.list.AllPokesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(navController)
                }
            }
        }
    }
}
@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationScreen.Home.route){
        composable(
            NavigationScreen.Home.route
            ){
            AllPokesScreen(hiltViewModel(), navController)
        }
        composable(
            NavigationScreen.Detail.route + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.StringType
            })){
            DetailPokeScreen(
                id = it.arguments?.getString("itemId"),
                viewModel = hiltViewModel(),
                navController = navController
                )
        }
    }
}
