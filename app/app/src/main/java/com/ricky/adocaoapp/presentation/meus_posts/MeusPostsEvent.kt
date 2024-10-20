package com.ricky.adocaoapp.presentation.meus_posts

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.presentation.home.HomeEvent

sealed interface MeusPostsEvent {
    data object OnLoadMore : MeusPostsEvent
    data object ClearError : MeusPostsEvent
    data object Resume : MeusPostsEvent
}