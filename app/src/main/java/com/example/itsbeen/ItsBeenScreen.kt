package com.example.itsbeen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.itsbeen.ui.HomeScreen

enum class Screen(val screenName: String) {
    Home(screenName = "home"),
}

@Composable
fun ItsBeenTopBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    color: Color = colorResource(id = R.color.faded_purple)
) {
    TopAppBar(
        title = { Text(
            text = title,
            color = Color.White
        ) },
        modifier = modifier,
        backgroundColor = color,
        contentColor = color
    )
}

@Composable
fun ItsBeenApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { ItsBeenTopBar() }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = Screen.Home.name) {
                HomeScreen()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ItsBeenApp()
}
