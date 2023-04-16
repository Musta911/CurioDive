package com.example.curiodive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amplifyframework.core.Amplify
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.screens.BaseScreen
import com.example.curiodive.ui.theme.CurioDiveTheme

@Composable
fun Home(navController: NavController?) {
    /*val email = AmplifyManager.Auth.getEmail()
    val username = AmplifyManager.Auth.getUsername()
    Column() {
        //Text(text = "Email : $email")
       Text(text = "Username : $username")
        Button(onClick = {
            AmplifyManager.Auth.signOut()
            navController?.navigate("login")
        }) {
            Text("Logout")
        }


    }
     */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home screen")
        Button(
            onClick = {
                AmplifyManager.Auth.signOut()
                navController?.navigate(BaseScreen.LoginScreen.route) {
                    popUpTo(BaseScreen.MainScreen.route)
                    {inclusive = true}
                }
            }
        ) {
            Text("Go to login")
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview() {
    CurioDiveTheme {
        Home(null)
    }
}