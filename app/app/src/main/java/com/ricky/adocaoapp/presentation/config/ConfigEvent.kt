package com.ricky.adocaoapp.presentation.config

sealed interface ConfigEvent {
    data object ChangeTheme : ConfigEvent
}