package com.ricky.adocaoapp.presentation.auth.login.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.presentation.auth.login.LoginEvent

@Composable
fun BtnCompose(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes title: Int
) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = {
            focusManager.clearFocus()
            onClick()
        },
        modifier = modifier
            .width(220.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge
                .copy(fontWeight = FontWeight.Bold)
        )
    }
}