package com.ricky.adocaoapp.presentation.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.navigation.Screens
import com.ricky.adocaoapp.presentation.auth.login.components.BtnCompose

@Composable
fun ConfigScreen(
    state: ConfigState,
    onEvent: (ConfigEvent) -> Unit,
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BtnCompose(
            onClick = { navController.navigate(Screens.RegisterScreen.route + "/${state.userId}") },
            title = R.string.atualizar_conta
        )
        Spacer(modifier = Modifier.height(16.dp))
        BtnCompose(
            onClick = { onEvent(ConfigEvent.ChangeTheme) },
            title = if (state.isDark) R.string.tema_claro else R.string.tema_escuro
        )
    }
}

@Preview
@Composable
private fun ConfigScreenPrev() {
    val context = LocalContext.current
    ConfigScreen(ConfigState(), {}, NavController(context))
}