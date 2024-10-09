package com.ricky.adocaoapp.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose
import com.ricky.adocaoapp.presentation.home.components.PetInfoItem
import com.ricky.adocaoapp.presentation.home.components.ToastError
import com.ricky.adocaoapp.presentation.home.components.TopAppBarCompose
import com.ricky.adocaoapp.utils.pet1

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {

    ToastError(error = state.error) {
        onEvent(HomeEvent.ClearError)
    }

    Scaffold(
        topBar = {
            TopAppBarCompose(
                search = state.search,
                onChangePesquisa = { onEvent(HomeEvent.OnChangePesquisa(it)) },
                onChangeFiltro = { onEvent(HomeEvent.OnChangeFiltro(it)) },
                onSearch = { onEvent(HomeEvent.OnSearch) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier
                    .padding(16.dp)
                    .size(70.dp),
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
        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            }

        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(listOf(pet1)) { item ->
                    PetInfoItem(
                        pet = item,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { }
                    )
                }
                if (state.isLoadingMore) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
                item {
                    AnimatedVisibility(visible = state.loadMoreVisible) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            BtnCompose(
                                onClick = { onEvent(HomeEvent.OnLoadMore) },
                                title = R.string.load_more
                            )
                        }
                    }
                }
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