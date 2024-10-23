package com.ricky.adocaoapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.navigation.Screens
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose
import com.ricky.adocaoapp.presentation.home.components.ToastError
import com.ricky.adocaoapp.utils.byteArrayToBitmap
import com.ricky.adocaoapp.utils.pet1

@Composable
fun DetailsScreen(
    navController: NavController,
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit
) {
    var foto: BitmapPainter? = null
    if (!state.isLoading && state.error.isBlank()) {
        val bitmap = byteArrayToBitmap(state.pet.foto)
        foto = BitmapPainter(bitmap.asImageBitmap())
    }

    ToastError(error = state.error) {
        onEvent(DetailsEvent.ClearError)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            onEvent(DetailsEvent.Reload)
        }
    }

    val context = LocalContext.current

    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
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
//                    painter = painterResource(id = R.drawable.blue_dog),
                    painter = if (foto != null) foto else painterResource(id = R.drawable.blue_dog),
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
                        Text(
                            text = state.pet.nome,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${state.pet.genero.value} - ${state.pet.idade.value}",
                                style = MaterialTheme.typography.titleLarge
                                    .copy(fontWeight = FontWeight.Bold)
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Text(
                                    text = state.pet.localizacao.value,
                                    style = MaterialTheme.typography.titleLarge
                                )

                                if (state.pet.distancia.isNotBlank()) {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.Map,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = state.pet.distancia,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }
                                }
                            }
                        }

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

                        if (state.pet.donoId != state.userId) {
//                            BtnCompose(
//                                modifier = Modifier
//                                    .align(Alignment.CenterHorizontally)
//                                    .padding(bottom = 16.dp),
//                                icon = Icons.Default.Phone,
//                                onClick = {
//                                    val intent = Intent(Intent.ACTION_DIAL).apply {
//                                        data = Uri.parse("tel:${state.pet.usuario.telefone}")
//                                    }
//                                    context.startActivity(intent)
//                                },
//                                title = R.string.chat,
//                                titleString = state.pet.usuario.telefone,
//                                color = MaterialTheme.colorScheme.primaryContainer,
//                                textColor = MaterialTheme.colorScheme.onPrimaryContainer
//                            )
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                BtnCompose(
                                    modifier = Modifier
                                        .padding(bottom = 16.dp)
                                        .weight(1f),
                                    onClick = { navController.navigate(Screens.ChatMsgScreen.route + "/${state.pet.donoId}/${state.pet.usuario.nome}") },
                                    title = R.string.chat,
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                BtnCompose(
                                    modifier = Modifier
                                        .padding(bottom = 16.dp)
                                        .weight(1f),
                                    icon = Icons.Default.Phone,
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:${state.pet.usuario.telefone}")
                                        }
                                        context.startActivity(intent)
                                    },
                                    title = R.string.chat,
                                    titleString = state.pet.usuario.telefone,
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }

                        }

//

                    }
                }
            }
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    if (state.pet.donoId == state.userId) {
                        IconButton(
                            onClick = {
                                navController.navigate(Screens.Form.route + "/${state.pet.id}")
                            }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
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
private fun DetailsScreenPrev() {
    val context = LocalContext.current
    val navController = NavController(context)
    DetailsScreen(
        navController = navController,
        state = DetailsState(pet = pet1),
        {}
    )
}