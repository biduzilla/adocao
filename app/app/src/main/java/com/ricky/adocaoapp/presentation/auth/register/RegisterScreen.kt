package com.ricky.adocaoapp.presentation.auth.register

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose

@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
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
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.criar_conta),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    TextFieldCompose(
                        value = state.nome,
                        isError = state.onErrorNome,
                        label = R.string.nome,
                        icon = Icons.Default.Person,
                        ime = ImeAction.Next
                    ) {

                    }

                    TextFieldCompose(
                        value = state.email,
                        isError = state.onErrorEmail,
                        label = R.string.email,
                        icon = Icons.Default.Email,
                        ime = ImeAction.Next
                    ) {

                    }

                    TextFieldCompose(
                        value = state.telefone,
                        isError = state.onErrorTelefone,
                        label = R.string.telefone,
                        icon = Icons.Default.Phone,
                        keyboardType = KeyboardType.Phone,
                        ime = ImeAction.Next
                    ) {

                    }

                    TextFieldCompose(
                        value = state.senha,
                        isError = state.onErrorSenha,
                        label = R.string.senha,
                        icon = Icons.Default.Key,
                        ime = ImeAction.Next
                    ) {

                    }

                    TextFieldCompose(
                        value = state.confirmarSenha,
                        isError = state.onErrorConfirmarSenha,
                        label = R.string.confirm_senha,
                        icon = Icons.Default.Key,
                        ime = ImeAction.Done
                    ) {

                    }

                    if (state.loading) {
                        CircularProgressIndicator()
                    } else {
                        Spacer(modifier = Modifier.height(48.dp))
                        Column {
                            Button(
                                onClick = { },
                                modifier = Modifier
                                    .width(220.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = stringResource(id = R.string.cadastrar),
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
private fun PreviewRegisterScreen() {
    RegisterScreen(state = RegisterState()) {

    }
}