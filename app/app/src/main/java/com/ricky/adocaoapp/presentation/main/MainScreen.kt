package com.ricky.adocaoapp.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.home.HomeEvent
import com.ricky.adocaoapp.presentation.home.components.ToppAppBarCompose
import com.ricky.adocaoapp.presentation.main.components.MainTopBar

@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = "${stringResource(id = R.string.bem_vindo)} ${state.usuario.nome}",
                onSair = { onEvent(MainEvent.OnSair) },
                onClickConfig = { },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MainScreen(MainState(), {})
}