package com.ricky.adocaoapp.presentation.meus_posts

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.Usuario

data class MeusPostsState(
    var isLoading: Boolean = false,
    var isLoadingMore: Boolean = false,
    val loadMoreVisible: Boolean = false,
    var pets: List<Pet> = emptyList(),
    val page: Int = 0,
    val error: String = "",
    val idUser:String = "",
)
