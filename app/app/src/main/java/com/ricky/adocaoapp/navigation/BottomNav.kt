package com.ricky.adocaoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ricky.adocaoapp.presentation.home.HomeScreen
import com.ricky.adocaoapp.presentation.home.HomeViewModel

@Composable
fun BottomNav(
    navHostController: NavHostController,
    navController: NavController
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomScreens.HomeScreen.route
    ) {
        composable(BottomScreens.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()

            HomeScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }

}