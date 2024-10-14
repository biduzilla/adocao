package com.ricky.adocaoapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.utils.byteArrayToBitmap
import com.ricky.adocaoapp.utils.pet1

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PetInfoItem(
    modifier: Modifier = Modifier,
    pet: Pet
) {

    val bitmap = byteArrayToBitmap(pet.foto)
    val foto = BitmapPainter(bitmap.asImageBitmap())

    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    ),
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterStart,
                painter = foto,
//                painter = painterResource(id = R.drawable.blue_dog),
                contentDescription = pet.nome
            )
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = pet.nome,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = pet.localizacao.value,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Row {
                            Icon(imageVector = Icons.Default.Map, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = pet.distancia,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                    }
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Tag(text = pet.status.value)
                    SexoTag(sexo = pet.genero)
                    Tag(text = pet.tamanho.name)
                    Tag(text = pet.tipoAnimal.value)
                    Tag(text = pet.idade.value)

                }
            }
        }
    }
}

@Composable
fun SexoTag(sexo: PetGeneroEnum, modifier: Modifier = Modifier) {
    val isDark = isSystemInDarkTheme()
    val color = if (sexo == PetGeneroEnum.MACHO) Color.Blue else Color.Magenta

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color)
    ) {
        Text(
            text = sexo.toString(),
            modifier = Modifier.padding(
                start = 12.dp,
                top = 4.dp,
                end = 12.dp,
                bottom = 6.dp
            ),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer, // Usar cor do tema
    textColor: Color = MaterialTheme.colorScheme.onPrimary,

    ) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                start = 12.dp,
                top = 4.dp,
                end = 12.dp,
                bottom = 6.dp
            ),
            color = textColor,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Composable
private fun PetItemPrev() {
    PetInfoItem(pet = pet1)
}