package com.example.curiodive.screens.loginscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.curiodive.login.ui.theme.CurioDiveTheme
import com.example.curiodive.screens.BaseScreen

@Composable
fun RememberPassword(navController: NavController?) {
    var email by remember { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "Remember password screen")
        OutlinedTextField(value = email, onValueChange = {email = it}, label = {Text("Email")})

        Button(onClick = { navController?.navigate(LoginScreen.ChangePassword.route) }) {
            Text(text = "Reset password")
        }
        Button(onClick = { navController?.navigate(LoginScreen.Signin.route) }) {
            Text(text = "Go back to signin")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RememberPasswordPreview() {
    CurioDiveTheme {
        RememberPassword(null)
    }
}