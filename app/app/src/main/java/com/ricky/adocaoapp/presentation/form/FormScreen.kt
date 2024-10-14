package com.ricky.adocaoapp.presentation.form

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.ricky.adocao.enums.PetTipoAnimalEnum
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.domain.enums.PetCidadeEnum
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.enums.PetIdadeEnum
import com.ricky.adocaoapp.domain.enums.PetStatusEnum
import com.ricky.adocaoapp.domain.enums.PetTamanhoEnum
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose
import com.ricky.adocaoapp.presentation.details.DetailsEvent
import com.ricky.adocaoapp.presentation.form.components.DialogRemover
import com.ricky.adocaoapp.presentation.form.components.DropdownCompose
import com.ricky.adocaoapp.presentation.form.components.ModalBottomSheetCompose
import com.ricky.adocaoapp.presentation.home.components.ToastError
import com.ricky.adocaoapp.utils.getTempUri
import com.ricky.adocaoapp.utils.rememberImeState

@Composable
fun FormScreen(
    state: FormState,
    navController: NavController,
    onEvent: (FormEvent) -> Unit
) {
    val context = LocalContext.current
    val tempUri = remember { mutableStateOf<Uri?>(null) }
    val scrollState = rememberScrollState()
    val imeState = rememberImeState()
    val focusManager = LocalFocusManager.current
    val photoPicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                onEvent(FormEvent.SelectPhoto(it, context))
            })

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                tempUri.value?.let {
                    onEvent(FormEvent.SelectPhoto(it, context))
                }
            }
        }
    )

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                tempUri.value = context.getTempUri()
                tempUri.value?.let {
                    cameraLauncher.launch(it)
                }
            } else {
                Toast.makeText(context, "camera permission is denied", Toast.LENGTH_SHORT).show()
            }
        }

//    LaunchedEffect(key1 = imeState.value) {
//        if (imeState.value) {
//            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
//        }
//    }

    ToastError(error = state.error) {
        onEvent(FormEvent.ClearError)
    }

    if (state.onErrorPhoto) {
        Toast.makeText(context, "Escolha uma foto", Toast.LENGTH_SHORT).show()
    }

    if (state.isOk) {
        navController.popBackStack()
    }

    if (state.isShowDialogRemover) {
        DialogRemover(
            onDimiss = { onEvent(FormEvent.ShowDialogRemover) },
            onRemover = {
                onEvent(FormEvent.DeletePet)
            })
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .padding(12.dp),
                onClick = {
                    navController.popBackStack()
                    focusManager.clearFocus()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (state.isUpdate) {
                IconButton(
                    modifier = Modifier
                        .padding(12.dp),
                    onClick = {
                        onEvent(FormEvent.DeletePet)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()

        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                        .verticalScroll(scrollState)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = if (state.isUpdate) R.string.atualizar_pet else R.string.cadastrar_animal),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp)
                            .clickable {
                                focusManager.clearFocus()
                                onEvent(FormEvent.ShowBottomSheet)
                            },
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.elevatedCardElevation(10.dp)
                    ) {
                        if (state.foto == null) {
                            Icon(
                                imageVector = Icons.Default.AddAPhoto,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            )
                        } else {
                            Image(
                                bitmap = state.foto!!.asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    TextFieldCompose(
                        value = state.nome,
                        isError = state.onErrorNome,
                        label = R.string.nome,
                        icon = Icons.Default.Pets
                    ) {
                        onEvent(FormEvent.OnChangeNome(it))
                    }

                    DropdownCompose(
                        label = R.string.idade,
                        value = state.idade.value,
                        list = PetIdadeEnum.entries.toTypedArray()
                    ) {
                        onEvent(FormEvent.OnChangeIdade(it))
                    }
                    DropdownCompose(
                        label = R.string.cidade,
                        value = state.cidade.value,
                        list = PetCidadeEnum.entries.toTypedArray()
                    ) {
                        onEvent(FormEvent.OnChangeCidade(it))
                    }
                    TextFieldCompose(
                        value = state.descricao,
                        isError = state.onErrorDescricao,
                        label = R.string.descricao,
                        icon = Icons.Default.Description
                    ) {
                        onEvent(FormEvent.OnChangeDescricao(it))
                    }
                    DropdownCompose(
                        label = R.string.genero,
                        value = state.genero.value,
                        list = PetGeneroEnum.entries.toTypedArray()
                    ) {
                        onEvent(FormEvent.OnChangeGenero(it))
                    }
                    DropdownCompose(
                        label = R.string.especie,
                        value = state.especie.value,
                        list = PetTipoAnimalEnum.entries.toTypedArray()
                    ) {
                        onEvent(FormEvent.OnChangeEspecie(it))
                    }
                    DropdownCompose(
                        label = R.string.status,
                        value = state.status.value,
                        list = PetStatusEnum.entries.toTypedArray()
                    ) {
                        onEvent(FormEvent.OnChangeStatus(it))
                    }
                    DropdownCompose(
                        label = R.string.tamanho,
                        value = state.tamanho.value,
                        list = PetTamanhoEnum.entries.toTypedArray()
                    ) {
                        onEvent(FormEvent.OnChangeTamanho(it))
                    }

                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        BtnCompose(
                            onClick = { onEvent(FormEvent.AddPet(context)) },
                            title = if (state.isUpdate) R.string.atualizar else R.string.cadastrar
                        )
                    }
                }
            }
        }
        if (state.isShowBottomSheet) {
            ModalBottomSheetCompose(
                onDismiss = { onEvent(FormEvent.ShowBottomSheet) },
                onTakePhotoClick = {
                    focusManager.clearFocus()
                    onEvent(FormEvent.ShowBottomSheet)
                    val permission = Manifest.permission.CAMERA
                    if (ContextCompat.checkSelfPermission(
                            context,
                            permission
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        tempUri.value = context.getTempUri()
                        tempUri.value?.let {
                            cameraLauncher.launch(it)
                        }
                    } else {
                        cameraPermissionLauncher.launch(permission)
                    }
                },
                onPhotoGalleryClick = {
                    focusManager.clearFocus()
                    onEvent(FormEvent.ShowBottomSheet)
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                })
        }

    }
}


@Preview
@Composable
private fun FormScreenPrev() {
    val context = LocalContext.current
    val navController = NavController(context)
    FormScreen(state = FormState(), navController = navController) {

    }
}