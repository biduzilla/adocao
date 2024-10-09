package com.ricky.adocaoapp.utils

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ricky.adocao.enums.*
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.enums.PetIdadeEnum
import com.ricky.adocaoapp.domain.enums.PetStatusEnum
import com.ricky.adocaoapp.domain.enums.PetTamanhoEnum
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.Usuario
import java.time.LocalDate

val pet1 = Pet(
    id = "1",
    nome = "Rex",
    idade = PetIdadeEnum.FILHOTE,
    localizacao = PetCidadeEnum.AGUAS_CLARAS,
    lat = -15_830_853,
    long = -47_949_365,
    usuario = Usuario(id = "10", nome = "João"),
    descricao = "Cachorro brincalhão e cheio de energia."
            + "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
    genero = PetGeneroEnum.MACHO,
    dataPublicacao = LocalDate.of(2024, 9, 1),
    foto = ByteArray(0),  // Suponha que a foto seria um array de bytes
    status = PetStatusEnum.PERDIDO,
    tipoAnimal = PetTipoAnimalEnum.CACHORRO,
    tamanho = PetTamanhoEnum.GRANDE,
    donoId = "100",
    distancia = "5 km"
)

val pet2 = Pet(
    id = "2",
    nome = "Luna",
    idade = PetIdadeEnum.ADULTO,
    localizacao = PetCidadeEnum.SOBRADINHO,
    lat = -15_650_000,
    long = -47_800_000,
    usuario = Usuario(id = "20", nome = "Maria"),
    descricao = "Gata calma, adora carinho.",
    genero = PetGeneroEnum.FEMEA,
    dataPublicacao = LocalDate.of(2024, 9, 2),
    foto = ByteArray(0),
    status = PetStatusEnum.ACHADO,
    tipoAnimal = PetTipoAnimalEnum.GATO,
    tamanho = PetTamanhoEnum.PEQUENO,
    donoId = "200",
    distancia = "10 km"
)

val pet3 = Pet(
    id = "3",
    nome = "Thor",
    idade = PetIdadeEnum.IDOSO,
    localizacao = PetCidadeEnum.TAGUATINGA,
    lat = -15_840_000,
    long = -47_980_000,
    usuario = Usuario(id = "30", nome = "Carlos"),
    descricao = "Cachorro idoso, tranquilo e companheiro.",
    genero = PetGeneroEnum.MACHO,
    dataPublicacao = LocalDate.now(),
    foto = ByteArray(0),
    status = PetStatusEnum.ADOCAO,
    tipoAnimal = PetTipoAnimalEnum.CACHORRO,
    tamanho = PetTamanhoEnum.MEDIO,
    donoId = "300",
    distancia = "2 km"
)
