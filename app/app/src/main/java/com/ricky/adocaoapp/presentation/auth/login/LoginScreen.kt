package com.ricky.adocaoapp.presentation.auth.login

import android.widget.Toast
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.navigation.Screens
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose
import com.ricky.adocaoapp.utils.rememberImeState

@Composable
fun LoginScreen(
    navController: NavController,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val imeState = rememberImeState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    if (state.error.isNotBlank()) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        onEvent(LoginEvent.ClearError)
    }

    if (state.onLogin) {
        navController.navigate(Screens.MainScreen.route)
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState),
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Icon(
                    modifier = Modifier.size(200.dp),
                    imageVector = Icons.Default.Pets,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Spacer(modifier = Modifier.height(56.dp))
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
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    label = R.string.senha,
                    icon = Icons.Default.Key,
                    ime = ImeAction.Done
                ) {
                    onEvent(LoginEvent.OnChangeSenha(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(modifier = Modifier.align(Alignment.End), onClick = {
                    focusManager.clearFocus()
                    navController.navigate(Screens.ForgetPasswordScreen.route)
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
                            onClick = {
                                focusManager.clearFocus()
                                onEvent(LoginEvent.OnLogin)
                            },
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

                    }
                }
                Spacer(modifier = Modifier.height(26.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.nao_tem_conta),
                        style = MaterialTheme.typography.labelLarge
                    )
                    TextButton(onClick = {
                        focusManager.clearFocus()
                        navController.navigate(Screens.RegisterScreen.route)

                    }) {
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

@Preview
@Composable
private fun PreviewLoginScreen() {
    val context = LocalContext.current
    val navController = NavController(context)
    LoginScreen(navController, LoginState(true)) {}
}