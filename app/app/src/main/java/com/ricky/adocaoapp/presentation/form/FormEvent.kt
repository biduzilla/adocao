package com.ricky.adocaoapp.presentation.form

import android.content.Context
import android.net.Uri
import com.ricky.adocao.enums.PetTipoAnimalEnum
import com.ricky.adocaoapp.domain.enums.PetCidadeEnum
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.enums.PetIdadeEnum
import com.ricky.adocaoapp.domain.enums.PetStatusEnum
import com.ricky.adocaoapp.domain.enums.PetTamanhoEnum

sealed interface FormEvent {
    data object ShowBottomSheet : FormEvent
    data class OnChangeNome(val nome: String) : FormEvent
    data class OnChangeDescricao(val descricao: String) : FormEvent
    data class OnChangeMicroChip(val chip: String) : FormEvent
    data class OnChangeIdade(val idade: PetIdadeEnum) : FormEvent
    data class OnChangeCidade(val cidade: PetCidadeEnum) : FormEvent
    data class OnChangeGenero(val genero: PetGeneroEnum) : FormEvent
    data class OnChangeEspecie(val especia: PetTipoAnimalEnum) : FormEvent
    data class OnChangeStatus(val status: PetStatusEnum) : FormEvent
    data class OnChangeTamanho(val tamanho:PetTamanhoEnum) : FormEvent
    data class SelectPhoto(val uri: Uri?, val context: Context) : FormEvent
    data object ShowDialogRemover : FormEvent
    data class AddPet(val context: Context) : FormEvent
    data object DeletePet : FormEvent
}