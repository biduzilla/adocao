package com.ricky.adocaoapp.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.navigation.BottomNav
import com.ricky.adocaoapp.navigation.Screens
import com.ricky.adocaoapp.presentation.home.components.ToastError
import com.ricky.adocaoapp.presentation.main.components.BottomBar
import com.ricky.adocaoapp.presentation.main.components.MainTopBar

@Composable
fun MainScreen(
    navController: NavController,
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    val navControllerBottom = rememberNavController()

    ToastError(error = state.error) {
        onEvent(MainEvent.ClearError)
    }

    if(state.onSair){
        navController.navigate(Screens.LoginScreen.route){
            popUpTo(navController.graph.startDestinationId){
                inclusive=true
            }
        }
    }

    Scaffold(
        topBar = {
            MainTopBar(
                title = "${stringResource(id = R.string.bem_vindo)} ${state.usuario.nome}",
                onSair = { onEvent(MainEvent.OnSair) },
                onClickConfig = { },
            )
        },
        bottomBar = {
            BottomBar(navController = navControllerBottom)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            BottomNav(
                navHostController = navControllerBottom,
                navController = navController
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    val context = LocalContext.current
    MainScreen(NavController(context), MainState(), {})
}