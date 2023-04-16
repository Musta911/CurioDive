package com.example.curiodive.screens.loginscreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.login.ui.theme.CurioDiveTheme
import com.example.curiodive.screens.BaseScreen

@Composable
fun Signup(navController: NavController?) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var checkPrivacy by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "Sign up screen")
        OutlinedTextField(value = email, onValueChange = {email = it}, label = {Text("Email address")})
        OutlinedTextField(value = username, onValueChange = {username = it}, label = {Text("Username")})
        OutlinedTextField(value = password, onValueChange = {password = it}, label = {Text("Password")})
        OutlinedTextField(value = confirmPassword, onValueChange = {confirmPassword = it}, label = {Text("Confirm password")})

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checkPrivacy, onCheckedChange = {
                checkPrivacy = it
            })
            Text(text = "I have read and agree the Privacy Policy and terms of use")
        }

        Button(
            onClick = {
                if(AmplifyManager.Auth.signUp(email,password,username)) {
                    navController?.navigate("${LoginScreen.ConfirmSignup.route}/$email")
                } else {
                    Toast.makeText(context,"Signup FAIL",Toast.LENGTH_LONG).show()
                }
            }
        ) {
            Text(text = "Sign up")
        }
        Row() {
            Text("Already have an account?")
            Text(
                text = "Sign in",
                modifier = Modifier.clickable { navController?.navigate(LoginScreen.Signin.route) },
                color = Color.Red
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupPreview() {
    CurioDiveTheme {
        Signup(navController = null)
    }
}