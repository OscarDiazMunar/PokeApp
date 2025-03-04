package com.oscar.pokemonapp.presentation.presentation_allpokes.login

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)