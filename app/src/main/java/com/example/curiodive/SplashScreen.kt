package com.example.curiodive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.login.ui.theme.CurioDiveTheme
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController?) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Green)
            .fillMaxSize()) {
        Text(text = "CurioDive", style = MaterialTheme.typography.titleLarge)
    }
    LaunchedEffect(key1 = true) {
        delay(5000)
        if (AmplifyManager.Auth.isSignedIn()) {
            navController?.navigate("home")
        } else {
            navController?.navigate("login")
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    CurioDiveTheme {
        Splash(navController = null)
    }
}