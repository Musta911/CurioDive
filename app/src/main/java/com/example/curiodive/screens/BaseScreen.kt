package com.example.curiodive.screens

sealed class BaseScreen(val route : String) {
    object LoginScreen : BaseScreen(route = "login")
    object MainScreen : BaseScreen(route = "main")
}
