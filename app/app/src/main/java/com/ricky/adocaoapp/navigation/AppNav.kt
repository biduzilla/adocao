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
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ricky.adocaoapp.presentation.auth.forget_password.ForgetPassScreen
import com.ricky.adocaoapp.presentation.auth.forget_password.ForgetPassViewModel
import com.ricky.adocaoapp.presentation.auth.login.LoginScreen
import com.ricky.adocaoapp.presentation.auth.login.LoginViewModel
import com.ricky.adocaoapp.presentation.auth.register.RegisterScreen
import com.ricky.adocaoapp.presentation.auth.register.RegisterViewModel
import com.ricky.adocaoapp.presentation.chat_msg.ChatMsgScreen
import com.ricky.adocaoapp.presentation.chat_msg.ChatMsgViewModel
import com.ricky.adocaoapp.presentation.details.DetailsScreen
import com.ricky.adocaoapp.presentation.details.DetailsViewModel
import com.ricky.adocaoapp.presentation.form.FormScreen
import com.ricky.adocaoapp.presentation.form.FormViewModel
import com.ricky.adocaoapp.presentation.main.MainScreen
import com.ricky.adocaoapp.presentation.main.MainViewModel
import com.ricky.adocaoapp.presentation.splash.SplashScreen
import com.ricky.adocaoapp.presentation.splash.SplashViewModel
import com.ricky.adocaoapp.utils.Constants

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNav() {
    val navController = rememberAnimatedNavController()

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

        composableSlideHorizontally(Screens.RegisterScreen.route + "/{${Constants.PARAM_USER_ID}}") {
            val viewModel = hiltViewModel<RegisterViewModel>()
            val state by viewModel.state.collectAsState()

            RegisterScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.ForgetPasswordScreen.route) {
            val viewModel = hiltViewModel<ForgetPassViewModel>()
            val state by viewModel.state.collectAsState()

            ForgetPassScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.Details.route + "/{${Constants.PARAM_PET_ID}}") {
            val viewModel = hiltViewModel<DetailsViewModel>()
            val state by viewModel.state.collectAsState()

            DetailsScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.Form.route + "/{${Constants.PARAM_PET_ID}}") {
            val viewModel = hiltViewModel<FormViewModel>()
            val state by viewModel.state.collectAsState()

            FormScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.Form.route) {
            val viewModel = hiltViewModel<FormViewModel>()
            val state by viewModel.state.collectAsState()

            FormScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(Screens.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            val state by viewModel.state.collectAsState()

            MainScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composableSlideHorizontally(Screens.ChatMsgScreen.route + "/{${Constants.PARAM_RECEIVER_ID}}/{${Constants.PARAM_RECEIVER_NOME}}") {
            val viewModel = hiltViewModel<ChatMsgViewModel>()
            val state by viewModel.state.collectAsState()

            ChatMsgScreen(
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