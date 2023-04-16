package com.example.curiodive.screens.mainscreens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.curiodive.Home
import com.example.curiodive.login.LoginScreen
import com.example.curiodive.login.RegisterConfirmScreen
import com.example.curiodive.login.RegisterScreen
import com.example.curiodive.login.RememberScreen
import com.example.curiodive.mainactivity.BottomNavigationBar
import com.example.curiodive.screens.loginscreens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseMainScreen(navControllerBaseScreen: NavController)  {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NavigationBar(navController = navController) },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination =  MainScreen.Home.route ) {
                composable(MainScreen.Home.route) {
                    Home(navControllerBaseScreen)
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
fun NavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(MainScreen.Home, MainScreen.Dives, MainScreen.Topics, MainScreen.Friends, MainScreen.Profile)
    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null)},
                label = { Text(text = stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            Log.d("stackSize","----size = ${navController.backQueue.size}")
                            launchSingleTop = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            )
        }

    }
}
