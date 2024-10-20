package com.ricky.adocaoapp.presentation.meus_posts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.navigation.Screens
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose
import com.ricky.adocaoapp.presentation.home.components.PetInfoItem
import com.ricky.adocaoapp.presentation.home.components.ToastError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusPostsScreen(
    state: MeusPostsState,
    onEvent: (MeusPostsEvent) -> Unit,
    navController: NavController
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            onEvent(MeusPostsEvent.Resume)
        }
    }

    ToastError(error = state.error) {
        onEvent(MeusPostsEvent.ClearError)
    }

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.clip(
                RoundedCornerShape(
                    bottomEnd = 50.dp,
                    bottomStart = 50.dp
                )
            ),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.meus_posts),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                })
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
                                onClick = { onEvent(MeusPostsEvent.OnLoadMore) },
                                title = R.string.load_more
                            )
                        }
                    }
                }
            }
        }
    }
}