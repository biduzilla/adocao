package com.ricky.adocao.models

import com.ricky.adocao.enums.*
import jakarta.persistence.*
import lombok.Data
import java.time.Instant
import java.util.*

@Entity
@Data
data class Pet(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PET_ID")
    var id: String = "",

    @Column(name = "NOME")
    var nome: String = "",

    @Column(name = "IDADE")
    @Enumerated(EnumType.STRING)
    var idade: PetIdadeEnum = PetIdadeEnum.ADULTO,

    @Column(name = "LOCALIZACAO")
    var localizacao: PetCidadeEnum = PetCidadeEnum.NUCLEO_BANDEIRANTE,

    @Column(name = "LATITUDE")
    var lat: Long,

    @Column(name = "LONGITUDE")
    var long: Long,

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    var usuario: Usuario = Usuario(),

    @Column(name = "DESCRICAO")
    var descricao: String = "",

    @Enumerated(EnumType.STRING)
    @Column(name = "GENERO")
    var genero: PetGeneroEnum = PetGeneroEnum.FEMEA,

    @Column(name = "DATA_PUBLICACAO")
    @Temporal(TemporalType.DATE)
    var dataPublicacao: Date = Date.from(Instant.now()),

    @Lob
    @Column(name = "FOTO")
    var foto: ByteArray = ByteArray(0),

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    var status: PetStatusEnum = PetStatusEnum.ACHADO,

    @Column(name = "TIPO_ANIMAL")
    @Enumerated(EnumType.STRING)
    var tipoAnimal: PetTipoAnimalEnum = PetTipoAnimalEnum.CACHORRO,

    @Column(name = "MICROCHIP")
    var microChip: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pet

        if (id != other.id) return false
        if (nome != other.nome) return false
        if (idade != other.idade) return false
        if (localizacao != other.localizacao) return false
        if (usuario != other.usuario) return false
        if (descricao != other.descricao) return false
        if (genero != other.genero) return false
        if (dataPublicacao != other.dataPublicacao) return false
        if (!foto.contentEquals(other.foto)) return false
        if (status != other.status) return false
        if (tipoAnimal != other.tipoAnimal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nome.hashCode()
        result = 31 * result + idade.hashCode()
        result = 31 * result + localizacao.hashCode()
        result = 31 * result + usuario.hashCode()
        result = 31 * result + descricao.hashCode()
        result = 31 * result + genero.hashCode()
        result = 31 * result + dataPublicacao.hashCode()
        result = 31 * result + foto.contentHashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + tipoAnimal.hashCode()
        return result
    }
}
