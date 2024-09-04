package com.ricky.adocaoapp.presentation.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
) {

    val context = LocalContext.current

    if (state.error.isNotBlank()) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    }

    if (state.onLogin) {
        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Pets,
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldCompose(
                    value = state.email,
                    isError = state.onErrorEmail,
                    label = R.string.email,
                    icon = Icons.Default.Email
                ) {
                    onEvent(LoginEvent.OnChangeEmail(it))
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldCompose(
                    value = state.senha,
                    isError = state.onErrorSenha,
                    label = R.string.senha,
                    icon = Icons.Default.Key,
                    ime = ImeAction.Done
                ) {
                    onEvent(LoginEvent.OnChangeSenha(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(modifier = Modifier.align(Alignment.End), onClick = {

                }) {
                    Text(
                        text = stringResource(id = R.string.esqueci_senha),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Column {
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .width(220.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = stringResource(id = R.string.acessar),
                                style = MaterialTheme.typography.titleLarge
                                    .copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.nao_tem_conta),
                                style = MaterialTheme.typography.labelLarge
                            )
                            TextButton(onClick = { }) {
                                Text(
                                    text = stringResource(id = R.string.criar_conta),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    )
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
private fun PreviewLoginScreen() {
    LoginScreen(LoginState(true)) {}
}