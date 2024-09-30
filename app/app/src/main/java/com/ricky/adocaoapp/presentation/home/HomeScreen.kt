package com.ricky.adocaoapp.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
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