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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.navigation.Screens
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose
import com.ricky.adocaoapp.presentation.home.components.PetInfoItem
import com.ricky.adocaoapp.presentation.home.components.ToastError
import com.ricky.adocaoapp.presentation.home.components.TopAppBarCompose

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
           onEvent(HomeEvent.Resume)
        }
    }

    ToastError(error = state.error) {
        onEvent(HomeEvent.ClearError)
    }

    Scaffold(
        topBar = {
            TopAppBarCompose(
                search = state.search,
                onChangePesquisa = { onEvent(HomeEvent.OnChangePesquisa(it)) },
                onChangeFiltro = { onEvent(HomeEvent.OnChangeFiltro(it)) },
                onSearch = { onEvent(HomeEvent.OnSearch) },
                filtro = state.filtroSearch,
                onClearFiltro = {onEvent(HomeEvent.ClearFiltro)}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.Form.route) },
                shape = CircleShape,
                modifier = Modifier
                    .padding(16.dp)
                    .size(70.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint=MaterialTheme.colorScheme.onPrimary
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
                items(state.pets) { item ->
                    PetInfoItem(
                        pet = item,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.navigate(Screens.Details.route + "/${item.id}") }
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