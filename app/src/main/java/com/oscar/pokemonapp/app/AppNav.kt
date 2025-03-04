package com.oscar.pokemonapp.app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.oscar.pokemonapp.commons.NavigationScreen
import com.oscar.pokemonapp.presentation.presentation_allpokes.detail.DetailPokeScreen
import com.oscar.pokemonapp.presentation.presentation_allpokes.list.AllPokesScreen
import com.oscar.pokemonapp.presentation.presentation_allpokes.login.GoogleAuthUiClient
import com.oscar.pokemonapp.presentation.presentation_allpokes.login.GoogleLoginScreen

@Composable
fun App(
    navController: NavHostController,
    applicationContext: Context,
    googleAuthUiClient: GoogleAuthUiClient
) {
    NavHost(navController = navController, startDestination = NavigationScreen.Login.route){
        composable(
            NavigationScreen.Login.route
        ) {
            GoogleLoginScreen(hiltViewModel(), navController, applicationContext, googleAuthUiClient)
        }

        composable(
            NavigationScreen.Home.route
        ){
            AllPokesScreen(hiltViewModel(), navController, googleAuthUiClient)
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