package com.ricky.adocaoapp.presentation.form

import android.graphics.Bitmap
import com.ricky.adocao.enums.PetTipoAnimalEnum
import com.ricky.adocaoapp.domain.enums.PetCidadeEnum
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.enums.PetIdadeEnum
import com.ricky.adocaoapp.domain.enums.PetStatusEnum
import com.ricky.adocaoapp.domain.enums.PetTamanhoEnum

data class FormState(
    var isUpdate:Boolean = false,
    var nome: String = "",
    var descricao:String ="",
    var idade: PetIdadeEnum = PetIdadeEnum.ADULTO,
    var cidade: PetCidadeEnum = PetCidadeEnum.AGUAS_CLARAS,
    var genero: PetGeneroEnum = PetGeneroEnum.FEMEA,
    var especie: PetTipoAnimalEnum = PetTipoAnimalEnum.CACHORRO,
    var status: PetStatusEnum = PetStatusEnum.ACHADO,
    var tamanho: PetTamanhoEnum = PetTamanhoEnum.MEDIO,
    var foto: Bitmap? = null,
    var isShowDataPicker: Boolean = false,
    var isShowBottomSheet: Boolean = false,
    var isShowDialogRemover: Boolean = false,
    var onErrorNome: Boolean = false,
    var onErrorDescricao: Boolean = false,
    var onErrorPhoto: Boolean = false,
    var isOk: Boolean = false,
    var isLoading: Boolean = false,
)