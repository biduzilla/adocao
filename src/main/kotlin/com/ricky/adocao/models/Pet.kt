package com.ricky.adocao.models

import com.ricky.adocao.enums.*
import jakarta.persistence.*
import lombok.Data
import java.sql.Date

@Entity
@Data
data class Pet(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PET_ID")
    val id: String,

    @Column(name = "NOME")
    private var nome: String,

    @Column(name = "IDADE")
    @Enumerated(EnumType.STRING)
    val idade: PetIdadeEnum,

    @Column(name = "LOCALIZACAO")
    val localizacao: PetCidadeEnum,

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    val usuario: Usuario,

    @Column(name = "DESCRICAO")
    val descricao: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "GENERO")
    val genero: PetGeneroEnum,

    @Column(name = "DATA_PUBLICACAO")
    @Temporal(TemporalType.DATE)
    val dataPublicacao: Date,

    @Lob
    @Column(name = "FOTO")
    val foto: ByteArray,

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    val status: PetStatusEnum,

    @Column(name = "TIPO_ANIMAL")
    @Enumerated(EnumType.STRING)
    val tipoAnimal: PetTipoAnimalEnum,
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
        var result = id?.hashCode() ?: 0
        result = 31 * result + (nome?.hashCode() ?: 0)
        result = 31 * result + (idade?.hashCode() ?: 0)
        result = 31 * result + (localizacao?.hashCode() ?: 0)
        result = 31 * result + (usuario?.hashCode() ?: 0)
        result = 31 * result + (descricao?.hashCode() ?: 0)
        result = 31 * result + (genero?.hashCode() ?: 0)
        result = 31 * result + (dataPublicacao?.hashCode() ?: 0)
        result = 31 * result + foto.contentHashCode()
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (tipoAnimal?.hashCode() ?: 0)
        return result
    }
}
