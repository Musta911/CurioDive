package com.example.curiodive.screens.loginscreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.login.RegisterConfirmScreen
import com.example.curiodive.login.ui.theme.CurioDiveTheme
import com.example.curiodive.screens.BaseScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Signin(navController: NavController?, navControllerBaseScreen : NavController?) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "Sign in screen")
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email address")}
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("Password")},
        )
        Text(
            text = "Don't remember password?",
            color = Color.Red,
            modifier = Modifier.clickable { navController?.navigate(LoginScreen.ChangePassword.route) }
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    val result = AmplifyManager.Auth.signIn(email,password)
                    if (result) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context,"Signed in successfully",Toast.LENGTH_LONG).show()
                            navControllerBaseScreen?.navigate(BaseScreen.MainScreen.route) {
                                popUpTo(BaseScreen.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context,"Username or password wrong",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                /*if (AmplifyManager.Auth.signIn(email,password)) {
                    Toast.makeText(context,"Signed in successfully",Toast.LENGTH_LONG).show()
                    navControllerBaseScreen?.navigate(BaseScreen.MainScreen.route) {
                        popUpTo(BaseScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    Toast.makeText(context,"Username or password wrong",Toast.LENGTH_LONG).show()
                }

                 */

            }
        ) {
            Text(text = "Sign in")
        }
        Row () {
            Text("Don't have an account?")
            Text(
                text = "Sign up",
                modifier = Modifier.clickable { navController?.navigate(LoginScreen.Signup.route) },
                color = Color.Red
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SigninPreview() {
    CurioDiveTheme {
        Signin(null,null)
    }
}