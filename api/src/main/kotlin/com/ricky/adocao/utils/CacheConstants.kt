package com.ricky.adocao.utils

enum class CacheConstants {
    NONE;

    companion object {
        const val USUARIOS_CACHE = "usuarios"
        const val PET_CACHE = "pets"
        const val PET_DONO_CACHE = "petsUser"
        const val REPORT_CACHE = "report"
    }
}