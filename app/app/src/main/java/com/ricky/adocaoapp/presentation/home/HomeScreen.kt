package com.ricky.adocaoapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.adocaoapp.presentation.home.components.PetInfoItem
import com.ricky.adocaoapp.presentation.home.components.ToppAppBarCompose
import com.ricky.adocaoapp.utils.pet1
import com.ricky.adocaoapp.utils.pet2
import com.ricky.adocaoapp.utils.pet3

@Composable
fun HomeScreen(
    navController: NavController,
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
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier.padding(16.dp).size(70.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(state.pets) { item ->
                PetInfoItem(
                    pet = item,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    val context = LocalContext.current
    HomeScreen(NavController(context), HomeState()) {}
}