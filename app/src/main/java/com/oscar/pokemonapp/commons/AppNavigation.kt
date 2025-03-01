package com.oscar.pokemonapp.commons

enum class Screen {
    HOME,
    DETAIL,
    LOGIN,
}
sealed class NavigationScreen(val route: String) {
    object Home : NavigationScreen(Screen.HOME.name)
    object Detail : NavigationScreen(Screen.DETAIL.name)
    object Login : NavigationScreen(Screen.LOGIN.name)
}