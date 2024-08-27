package com.ricky.adocaoapp.presentation.auth.login.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.ui.theme.ErrorLight

@Composable
fun TextFieldCompose(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    @StringRes errorText: Int? = null,
    @StringRes label: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: ImageVector? = null,
    isPassword: Boolean = false,
    ime: ImeAction = ImeAction.Next,
    onChange: (String) -> Unit
) {

    var hiddenPassword by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier,
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            value = value,
            onValueChange = { onChange(it) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                keyboardType = if (isPassword && hiddenPassword) KeyboardType.Password else keyboardType,
                imeAction = ime
            ),
            label = {
                if (label != null) {
                    Text(
                        text = stringResource(id = label),
                    )
                }
            },
            isError = isError,
            supportingText = {
                Text(
                    text = stringResource(id = errorText ?: R.string.campo_obrigatorio),
                    color = ErrorLight,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                ),
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { hiddenPassword = !hiddenPassword }) {
                        val iconShow =
                            if (hiddenPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        Icon(imageVector = iconShow, contentDescription = null)
                    }
                }
            },

            leadingIcon = icon?.let {
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = if (label != null) stringResource(id = label) else null
                    )
                }
            }

        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldComposePrev() {
    TextFieldCompose(
        modifier = Modifier.padding(16.dp),
        value = "value",
        isError = true,
        label = R.string.cadastrar_animal,
        errorText = R.string.confirm_senha_error
    ) {

    }
}