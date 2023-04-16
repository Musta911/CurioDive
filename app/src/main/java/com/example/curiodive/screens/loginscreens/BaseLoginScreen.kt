package com.example.curiodive.screens.loginscreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.curiodive.login.LoginScreen
import com.example.curiodive.login.RegisterConfirmScreen
import com.example.curiodive.login.RegisterScreen
import com.example.curiodive.login.RememberScreen
import com.example.curiodive.mainactivity.MainScreen
import com.example.curiodive.screens.mainscreens.MainScreen

@Composable
fun BaseLoginScreen(navController: NavController) {
    LoginNavigation(navControllerBaseScreen = navController)
}

@Composable
fun LoginNavigation(navControllerBaseScreen: NavController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  LoginScreen.Signin.route ) {
        composable(LoginScreen.Signin.route) {
            Signin(navController, navControllerBaseScreen)
        }
        composable(LoginScreen.Signup.route) {
            Signup(navController)
        }
        composable(LoginScreen.ChangePassword.route) {
            ChangePassword(navController)
        }
        composable("${LoginScreen.ConfirmSignup.route}/{email}") {
            val email = it.arguments?.getString("email")
            ConfirmSignup(navController = navController,email!!)
        }
        composable(LoginScreen.RememberPassword.route) {
            RememberPassword(navController = navController)
        }
        composable(LoginScreen.Tutorial.route) {
            Tutorial(navController, navControllerBaseScreen)
        }

    }
}