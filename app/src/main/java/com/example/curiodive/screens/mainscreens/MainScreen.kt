package com.example.curiodive.screens.mainscreens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.curiodive.R

sealed class MainScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : MainScreen("home", R.string.home, Icons.Filled.Home)
    object Dives : MainScreen("dives", R.string.dives, Icons.Filled.Notifications)
    object Topics : MainScreen("topics", R.string.topics, Icons.Filled.Search)
    object Friends : MainScreen("friends", R.string.friends, Icons.Filled.Groups)
    object Profile : MainScreen("profile", R.string.profile, Icons.Filled.AccountCircle)
}
