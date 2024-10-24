package com.ricky.adocaoapp.presentation.home

import com.ricky.adocaoapp.domain.models.FiltroSearch

sealed interface HomeEvent {
    data class OnChangeFiltro(val filtro: FiltroSearch) : HomeEvent
    data class OnChangePesquisa(val pesquisa: String) : HomeEvent
    data object OnSearch : HomeEvent
    data object OnLoadMore : HomeEvent
    data object ClearError : HomeEvent
    data object ClearFiltro : HomeEvent
    data object Resume : HomeEvent
}