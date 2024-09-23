package com.ricky.adocaoapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose

@Composable
fun ToppAppBarCompose(
    modifier: Modifier = Modifier,
    title: String,
    search: String,
    onVoltar: () -> Unit,
    onChangePesquisa: (String) -> Unit,
    onClickConfig: () -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            bottomEnd = 50.dp,
            bottomStart = 50.dp
        ),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onClickConfig }) {
                        Icon(
                            imageVector = Icons.Default.Pets,
                            contentDescription = title,
                            modifier = Modifier.size(38.dp),
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                IconButton(onClick = onVoltar) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Logout,
                        contentDescription = title
                    )
                }
            }
            TextFieldCompose(
                value = search,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                icon = Icons.Default.Search
            ) {
                onChangePesquisa(it)
            }
            IconButton(modifier = Modifier.align(Alignment.End),
                onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (!expanded) Icons.Filled.FilterList else Icons.Outlined.FilterList,
                    contentDescription = null
                )
            }


        }
    }
}

@Preview
@Composable
private fun ToppAppBarComposePrev() {
    ToppAppBarCompose(Modifier, "Ola teste", "", {}, { }, {})
}