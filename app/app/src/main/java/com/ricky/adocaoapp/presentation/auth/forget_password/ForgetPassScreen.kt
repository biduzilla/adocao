package com.ricky.adocaoapp.presentation.auth.forget_password

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose

@Composable
fun ForgetPassScreen(
    state: ForgetPassState,
    onEvent: (ForgetPassEvent) -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    if (state.error.isNotBlank()) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .background(Color.Green)
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(12.dp),
            onClick = { }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()

        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                        .verticalScroll(scrollState)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.recuperar_senha),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    TextFieldCompose(
                        value = state.email,
                        isError = state.onErrorEmail,
                        label = R.string.nome,
                        icon = Icons.Default.Email,
                        ime = ImeAction.Next
                    ) {
                        onEvent(ForgetPassEvent.OnChangeEmail(it))
                    }

                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Column {
                            Button(
                                onClick = {
                                    onEvent(ForgetPassEvent.OnSendEmail)
                                },
                                modifier = Modifier
                                    .width(220.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = stringResource(id = R.string.enviar_email),
                                    style = MaterialTheme.typography.titleLarge
                                        .copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }

                    TextFieldCompose(
                        value = state.cod,
                        isError = state.onErrorCod,
                        label = R.string.cod_invalido,
                        icon = Icons.Default.Numbers,
                        keyboardType = KeyboardType.Number,
                        errorText = R.string.cod_invalido,
                        ime = ImeAction.Next
                    ) {
                        onEvent(ForgetPassEvent.OnChangeCod(it))
                    }

                    if (state.isLoading && state.isEmailSend) {
                        CircularProgressIndicator()
                    } else if (!state.isLoading && state.isEmailSend) {
                        Column {
                            Button(
                                onClick = {
                                    onEvent(ForgetPassEvent.OnSendCod)
                                },
                                modifier = Modifier
                                    .width(220.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = stringResource(id = R.string.ver_cod),
                                    style = MaterialTheme.typography.titleLarge
                                        .copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }

                    TextFieldCompose(
                        value = state.senha,
                        isError = state.onErrorSenha,
                        label = R.string.senha,
                        icon = Icons.Default.Key,
                        ime = ImeAction.Next
                    ) {
                        onEvent(ForgetPassEvent.OnChangeSenha(it))
                    }

                    TextFieldCompose(
                        value = state.confirmSenha,
                        isError = state.onErrorConfirmSenha,
                        label = R.string.confirm_senha,
                        icon = Icons.Default.Key,
                        ime = ImeAction.Done,
                        errorText = R.string.confirm_senha_error
                    ) {
                        onEvent(ForgetPassEvent.OnChangeConfirmSenha(it))
                    }

                    if (state.isLoading && state.isCodVer) {
                        CircularProgressIndicator()
                    } else if (!state.isLoading && state.isCodVer) {
                        Column {
                            Button(
                                onClick = {
                                    onEvent(ForgetPassEvent.OnUpdatePassword)
                                },
                                modifier = Modifier
                                    .width(220.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = stringResource(id = R.string.alterar_senha),
                                    style = MaterialTheme.typography.titleLarge
                                        .copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ForgetPassPreview() {
    ForgetPassScreen(state = ForgetPassState()) {

    }
}