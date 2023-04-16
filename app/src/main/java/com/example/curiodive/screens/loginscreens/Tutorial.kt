package com.example.curiodive.screens.loginscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.curiodive.login.ui.theme.CurioDiveTheme
import com.example.curiodive.screens.BaseScreen

@Composable
fun Tutorial(navController: NavController, navControllerBaseScreen : NavController) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "Tutorial  screen")
        Button(onClick = { navControllerBaseScreen.navigate(BaseScreen.MainScreen.route) }) {
            Text(text = "Go to home")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TutorialPreview() {
    CurioDiveTheme {

    }
}