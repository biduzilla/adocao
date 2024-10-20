package com.ricky.adocaoapp.presentation.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose

@Composable
fun TopAppBarCompose(
    modifier: Modifier = Modifier,
    search: String,
    onChangePesquisa: (String) -> Unit,
    onChangeFiltro: (FiltroSearch) -> Unit,
    onClearFiltro: () -> Unit,
    onSearch: () -> Unit,
    filtro: FiltroSearch
) {

    var filtros by remember {
        mutableStateOf(filtro)
    }

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
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextFieldCompose(
                value = search,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                icon = Icons.Default.Search,
                ime = ImeAction.Done,
                onDone = {
                    onSearch()
                },
                label = R.string.pesquisar
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

            if (expanded) {
                FiltroSection(filtro = filtros) {
                    onChangeFiltro(it)
                    filtros = it
                }

                Button(
                    onClick = {
                        onClearFiltro()
                        filtros =FiltroSearch()
                        onChangeFiltro(filtros)
                    },
                    modifier = Modifier
                        .width(220.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(id = R.string.limpar_filtros),
                        style = MaterialTheme.typography.titleLarge
                            .copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Composable
fun FiltroCheckboxRow(
    labelId: Int,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    horizontalSpacing: Dp = 8.dp
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        Text(text = stringResource(id = labelId))
    }
}

@Composable
fun FiltroSection(
    filtro: FiltroSearch,
    onChangeFiltro: (FiltroSearch) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FiltroCheckboxRow(
                labelId = R.string.cachorro,
                checked = filtro.isDog,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isDog = it))
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.gato,
                checked = filtro.isCat,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isCat = it))
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FiltroCheckboxRow(
                labelId = R.string.achado,
                checked = filtro.isAchado,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isAchado = it))
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.perdido,
                checked = filtro.isPerdido,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isPerdido = it))
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.adocao,
                checked = filtro.isAdotar,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isAdotar = it))
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FiltroCheckboxRow(
                labelId = R.string.pequeno,
                checked = filtro.isPequeno,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isPequeno = it))
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.medio,
                checked = filtro.isMedio,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isMedio = it))
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.grande,
                checked = filtro.isGrande,
                onCheckedChange = {
                    onChangeFiltro(filtro.copy(isGrande = it))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FiltroCheckboxRow(
                labelId = R.string.macho,
                checked = filtro.isMacho,
                onCheckedChange = {
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.femea,
                checked = filtro.isFemea,
                onCheckedChange = {
                    onChangeFiltro(filtro)
                }
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FiltroCheckboxRow(
                labelId = R.string.filhote,
                checked = filtro.isFilhote,
                onCheckedChange = {
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.adulto,
                checked = filtro.isAdulto,
                onCheckedChange = {
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.idoso,
                checked = filtro.isIdoso,
                onCheckedChange = {
                    onChangeFiltro(filtro)
                }
            )
        }
    }

}


@Preview
@Composable
private fun TopAppBarComposePrev() {
    TopAppBarCompose(Modifier, "Ola teste", {}, {}, {}, {}, FiltroSearch())
}