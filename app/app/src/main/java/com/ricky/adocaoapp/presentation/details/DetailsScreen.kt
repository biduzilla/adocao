package com.ricky.adocaoapp.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose
import com.ricky.adocaoapp.presentation.home.HomeEvent
import com.ricky.adocaoapp.presentation.home.components.ToastError
import com.ricky.adocaoapp.utils.pet1

@Composable
fun DetailsScreen(
    navController: NavController,
    state: DetailsState,
    onEvent:(DetailsEvent)->Unit
) {
    //    val bitmap = byteArrayToBitmap(pet.foto)
//    val foto = BitmapPainter(bitmap.asImageBitmap())

    ToastError(error = state.error) {
        onEvent(DetailsEvent.ClearError)
    }

    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterStart,
                painter = painterResource(id = R.drawable.blue_dog),
                contentDescription = state.pet.nome
            )
        }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            Surface(
                modifier = Modifier
                    .weight(1.5f),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp
                ),
                color = MaterialTheme.colorScheme.primary
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = state.pet.nome,
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(top = 2.dp)
                        ) {
                            Text(
                                text = state.pet.localizacao.toString(),
                                style = MaterialTheme.typography.titleLarge
                            )
                            Row {
                                Icon(imageVector = Icons.Default.Map, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = state.pet.distancia,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }

                        }
                    }
                    Text(
                        text = "${state.pet.genero.value} - ${state.pet.idade.value}",
                        style = MaterialTheme.typography.titleLarge
                            .copy(fontWeight = FontWeight.Bold)
                    )
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 8.dp
                            ),
                            text = state.pet.status.value,
                            style = MaterialTheme.typography.titleLarge
                                .copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Text(
                        modifier = Modifier.weight(1f),
                        text = state.pet.descricao,
                        style = MaterialTheme.typography.bodyLarge
                            .copy(fontWeight = FontWeight.Bold)
                    )

                    BtnCompose(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp),
                        onClick = { },
                        title = R.string.chat,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        Column {
            IconButton(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(12.dp),
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPrev() {
    val context = LocalContext.current
    val navController = NavController(context)
    DetailsScreen(
        navController = navController,
        state = DetailsState(pet = pet1),
        {}
    )
}