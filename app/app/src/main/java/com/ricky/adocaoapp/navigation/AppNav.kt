package com.ricky.adocaoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.ricky.adocaoapp.presentation.auth.forget_password.ForgetPassScreen
import com.ricky.adocaoapp.presentation.auth.forget_password.ForgetPassViewModel
import com.ricky.adocaoapp.presentation.auth.login.LoginScreen
import com.ricky.adocaoapp.presentation.auth.login.LoginViewModel
import com.ricky.adocaoapp.presentation.auth.register.RegisterScreen
import com.ricky.adocaoapp.presentation.auth.register.RegisterViewModel
import com.ricky.adocaoapp.presentation.splash.SplashScreen
import com.ricky.adocaoapp.presentation.splash.SplashViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNav() {
    val navController = rememberNavController()

    AnimatedNavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composableSlideHorizontally(Screens.SplashScreen.route) {
            val viewModel = hiltViewModel<SplashViewModel>()
            val state by viewModel.state.collectAsState()

            SplashScreen(state = state, navController = navController)
        }

        composableSlideHorizontally(Screens.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsState()

            LoginScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.RegisterScreen.route) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            val state by viewModel.state.collectAsState()

            RegisterScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.RegisterScreen.route) {
            val viewModel = hiltViewModel<ForgetPassViewModel>()
            val state by viewModel.state.collectAsState()

            ForgetPassScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 700, // 300 - 1000
    exitOffset: Int = -700,
    popEnterOffset: Int = -700,
    popExitOffset: Int = 700,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)) { exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}