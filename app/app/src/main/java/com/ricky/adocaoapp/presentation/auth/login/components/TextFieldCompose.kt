package com.ricky.adocaoapp.presentation.auth.login.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    @StringRes label: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: ImageVector? = null,
    ime: ImeAction = ImeAction.Next,
    onChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        if (label != null) {
            Text(
                text = stringResource(id = label),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            )
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            value = value,
            onValueChange = { onChange(it) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                keyboardType = keyboardType,
                imeAction = ime
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                ),
            leadingIcon = if (icon != null) {
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = if (label != null) stringResource(id = label) else null
                    )
                }
            } else {
                null
            },
        )
        if (isError) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                text = stringResource(id = R.string.campo_obrigatorio),
                color = ErrorLight,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldComposePrev() {
    TextFieldCompose(
        modifier = Modifier.padding(16.dp),
        value = "value",
        isError = true,
        label = R.string.cadastrar_animal
    ) {

    }
}