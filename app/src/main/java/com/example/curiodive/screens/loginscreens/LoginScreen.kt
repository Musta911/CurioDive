package com.example.curiodive.screens.loginscreens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.curiodive.R
import com.example.curiodive.screens.mainscreens.MainScreen

sealed class LoginScreen(val route: String) {
    object Signin : LoginScreen("signin")
    object Signup : LoginScreen("signup")
    object ConfirmSignup : LoginScreen("confirmsignup")
    object RememberPassword : LoginScreen("rememberpassword")
    object ChangePassword : LoginScreen("changepassword")
    object Tutorial : LoginScreen("tutorial")
}
