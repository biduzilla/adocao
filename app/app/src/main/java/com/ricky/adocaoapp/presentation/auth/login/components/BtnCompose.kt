package com.ricky.adocaoapp.presentation.auth.login.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R

@Composable
fun BtnCompose(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes title: Int,
    icon: ImageVector? = null,
    titleString: String? = null,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = Color.White
) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = {
            focusManager.clearFocus()
            onClick()
        },
        modifier = modifier
            .width(240.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        if (icon != null) {
            Icon( imageVector = icon, contentDescription = titleString ?: stringResource(id = title))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = titleString ?: stringResource(id = title),
            style = MaterialTheme.typography.titleLarge
                .copy(fontWeight = FontWeight.Bold,)
        )
    }
}

@Preview
@Composable
private fun BtnComposePrev() {
    BtnCompose(onClick = {}, title = R.string.cadastrar, titleString = "(61)995932139", icon = Icons.Default.Phone)
}