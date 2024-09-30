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
fun ToppAppBarCompose(
    modifier: Modifier = Modifier,
    search: String,
    onChangePesquisa: (String) -> Unit,
    onChangeFiltro: (FiltroSearch) -> Unit,
    onSearch: () -> Unit,
) {

    var filtro = FiltroSearch()
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
                ime = ImeAction.Next,
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
                FiltroSection(expanded = expanded, filtro = filtro) { onChangeFiltro(it) }

                Button(
                    onClick = {
                        filtro = FiltroSearch()
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
            onCheckedChange = onCheckedChange
        )
        Text(text = stringResource(id = labelId))
    }
}

@Composable
fun FiltroSection(
    expanded: Boolean,
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
                checked = filtro.isCat,
                onCheckedChange = {
                    filtro.isCat = !filtro.isCat
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.gato,
                checked = filtro.isDog,
                onCheckedChange = {
                    filtro.isDog = !filtro.isDog
                    onChangeFiltro(filtro)
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
                    filtro.isAchado = !filtro.isAchado
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.perdido,
                checked = filtro.isPerdido,
                onCheckedChange = {
                    filtro.isPerdido = !filtro.isPerdido
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.adocao,
                checked = filtro.isAdotar,
                onCheckedChange = {
                    filtro.isAdotar = !filtro.isAdotar
                    onChangeFiltro(filtro)
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
                    filtro.isPequeno = !filtro.isPequeno
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.medio,
                checked = filtro.isMedio,
                onCheckedChange = {
                    filtro.isMedio = !filtro.isMedio
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.grande,
                checked = filtro.isGrande,
                onCheckedChange = {
                    filtro.isGrande = !filtro.isGrande
                    onChangeFiltro(filtro)
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
                    filtro.isMacho = !filtro.isMacho
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.femea,
                checked = filtro.isFemea,
                onCheckedChange = {
                    filtro.isFemea = !filtro.isFemea
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
                    filtro.isFilhote = !filtro.isFilhote
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.adulto,
                checked = filtro.isAdulto,
                onCheckedChange = {
                    filtro.isAdulto = !filtro.isAdulto
                    onChangeFiltro(filtro)
                }
            )
            FiltroCheckboxRow(
                labelId = R.string.idoso,
                checked = filtro.isIdoso,
                onCheckedChange = {
                    filtro.isIdoso = !filtro.isIdoso
                    onChangeFiltro(filtro)
                }
            )
        }
    }

}


@Preview
@Composable
private fun ToppAppBarComposePrev() {
    ToppAppBarCompose(Modifier, "Ola teste", {}, {},{})
}