package com.example.curiodive.screens.mainscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.curiodive.Home
import com.example.curiodive.ui.theme.CurioDiveTheme

@Composable
fun Dives() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dives screen")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DivesPreview() {
    CurioDiveTheme {
        Dives()
    }
}