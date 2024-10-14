package com.ricky.adocaoapp.utils

import com.ricky.adocao.enums.PetTipoAnimalEnum
import com.ricky.adocaoapp.domain.enums.PetCidadeEnum
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
    lat = -15.00,
    long = -47.00,
    usuario = Usuario(id = "10", nome = "João"),
    descricao = "Cachorro brincalhão e cheio de energia."
            + "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
    genero = PetGeneroEnum.MACHO,
    dataPublicacao = "1/1/1",
    foto ="",
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
    lat = -15.00,
    long = -47.00,
    usuario = Usuario(id = "20", nome = "Maria"),
    descricao = "Gata calma, adora carinho.",
    genero = PetGeneroEnum.FEMEA,
    dataPublicacao = "1/1/1",
    foto = "",
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
    lat = -15.00,
    long = -47.00,
    usuario = Usuario(id = "30", nome = "Carlos"),
    descricao = "Cachorro idoso, tranquilo e companheiro.",
    genero = PetGeneroEnum.MACHO,
    dataPublicacao = "1/1/1",
    foto = "",
    status = PetStatusEnum.ADOCAO,
    tipoAnimal = PetTipoAnimalEnum.CACHORRO,
    tamanho = PetTamanhoEnum.MEDIO,
    donoId = "300",
    distancia = "2 km"
)
