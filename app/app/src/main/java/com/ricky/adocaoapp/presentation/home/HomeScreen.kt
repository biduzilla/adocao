package com.ricky.adocaoapp.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ricky.adocaoapp.presentation.home.components.ToppAppBarCompose

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            ToppAppBarCompose(
                search = state.search,
                onChangePesquisa = { onEvent(HomeEvent.OnChangePesquisa(it)) },
                onChangeFiltro = { onEvent(HomeEvent.OnChangeFiltro(it)) })
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(HomeState()) {}
}