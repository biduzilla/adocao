package com.ricky.adocaoapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.home.components.ToppAppBarCompose

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            ToppAppBarCompose(
                title = "${stringResource(id = R.string.bem_vindo)} ${state.usuario.nome}",
                search = state.search,
                onSair = { onEvent(HomeEvent.OnSair) },
                onChangePesquisa = { onEvent(HomeEvent.OnChangePesquisa(it)) },
                onClickConfig = { },
                onChangeFiltro = { onEvent(HomeEvent.OnChangeFiltro(it)) })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(HomeState()) {}
}