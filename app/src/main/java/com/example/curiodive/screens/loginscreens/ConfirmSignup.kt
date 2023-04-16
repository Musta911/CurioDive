package com.example.curiodive.screens.loginscreens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.login.ui.theme.CurioDiveTheme
import com.example.curiodive.screens.BaseScreen

@Composable
fun ConfirmSignup(navController: NavController?,email : String) {
    val context = LocalContext.current
    var code by remember { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "Confirm sign up screen")
        OutlinedTextField(value = code, onValueChange = {code = it}, label = {Text("Confirmation code")})

        Button(
            onClick = {
                if (AmplifyManager.Auth.confirmSignUp(email,code)) {
                    navController?.navigate(LoginScreen.Tutorial.route)
                } else {Toast.makeText(context,"Fail CONFIRMATION",Toast.LENGTH_LONG).show()}
            }
        ) {
            Text(text = "Confirm and sign up")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfirmSignupPreview() {
    CurioDiveTheme {
        ConfirmSignup(navController = null,"")
    }
}