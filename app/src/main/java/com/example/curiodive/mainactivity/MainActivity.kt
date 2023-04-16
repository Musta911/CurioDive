@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.curiodive.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.example.curiodive.Home
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.login.*
import com.example.curiodive.screens.BaseScreen
import com.example.curiodive.screens.loginscreens.BaseLoginScreen
import com.example.curiodive.screens.mainscreens.*
import com.example.curiodive.ui.theme.CurioDiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.configure(applicationContext)
        setContent {
            CurioDiveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (AmplifyManager.Auth.isSignedIn()) {
                        BaseNavigation(screen = BaseScreen.MainScreen.route)
                    } else BaseNavigation(screen = BaseScreen.LoginScreen.route)


                }
            }
        }
    }
}

@Composable
fun BaseNavigation(screen: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  screen ) {
        composable(BaseScreen.LoginScreen.route) {
            BaseLoginScreen(navController)
        }
        composable(BaseScreen.MainScreen.route) {
            BaseMainScreen(navController)
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    CurioDiveTheme {
        MainScreen(screen = "home")
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(MainScreen.Home, MainScreen.Dives, MainScreen.Topics, MainScreen.Friends, MainScreen.Profile)
    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { screen.icon},
                label = { Text(text = stringResource(id = screen.resourceId))},
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(screen.route)
                }
            )
        }

    }
}

@Composable
fun MainScreen(screen : String) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {BottomNavigationBar(navController = navController)},
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination =  screen ) {
                composable("login") {
                    LoginScreen(navController = navController)
                }
                composable("signup") {
                    RegisterScreen(navController)
                }
                composable("remember_password") {
                    RememberScreen(navController)
                }
                composable("confirm_signup/{email}") {
                    val email = it.arguments?.getString("email")
                    RegisterConfirmScreen(navController = navController,email!!)
                }
                composable(MainScreen.Home.route) {
                    Home(navController)
                }
                composable(MainScreen.Dives.route) {
                    Dives()
                }
                composable(MainScreen.Topics.route) {
                    Topics()
                }
                composable(MainScreen.Friends.route) {
                    Friends()
                }
                composable(MainScreen.Profile.route) {
                    Profile()
                }
            }
        }
    }

}

@Composable
fun Navigation(screen : String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  screen ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("signup") {
            RegisterScreen(navController)
        }
        composable("remember_password") {
            RememberScreen(navController)
        }
        composable("confirm_signup/{email}") {
            val email = it.arguments?.getString("email")
            RegisterConfirmScreen(navController = navController,email!!)
        }
        composable("splash") {
            MainScreen(MainScreen.Home.route)
        }

    }
}