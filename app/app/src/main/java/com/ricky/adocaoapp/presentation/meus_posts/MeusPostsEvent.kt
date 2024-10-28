package com.ricky.adocaoapp.presentation.meus_posts

sealed interface MeusPostsEvent {
    data object OnLoadMore : MeusPostsEvent
    data object ClearError : MeusPostsEvent
    data object Resume : MeusPostsEvent
}