package com.ricky.adocaoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ricky.adocaoapp.presentation.chat.ChatScreen
import com.ricky.adocaoapp.presentation.chat.ChatViewModel
import com.ricky.adocaoapp.presentation.config.ConfigScreen
import com.ricky.adocaoapp.presentation.config.ConfigViewModel
import com.ricky.adocaoapp.presentation.home.HomeScreen
import com.ricky.adocaoapp.presentation.home.HomeViewModel
import com.ricky.adocaoapp.presentation.meus_posts.MeusPostsScreen
import com.ricky.adocaoapp.presentation.meus_posts.MeusPostsViewModel

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

        composable(BottomScreens.MeusPosts.route) {
            val viewModel = hiltViewModel<MeusPostsViewModel>()
            val state by viewModel.state.collectAsState()

            MeusPostsScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composable(BottomScreens.ConfigScreen.route) {
            val viewModel = hiltViewModel<ConfigViewModel>()
            val state by viewModel.state.collectAsState()

            ConfigScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composable(BottomScreens.ChatScreen.route) {
            val viewModel = hiltViewModel<ChatViewModel>()
            val state by viewModel.state.collectAsState()

            ChatScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }

}