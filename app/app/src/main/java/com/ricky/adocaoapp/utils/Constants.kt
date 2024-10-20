package com.ricky.adocaoapp.utils

object Constants {
    const val SETTINGS: String = "settings"
    const val IS_DARK_MODE: String = "darkMode"
    const val BASE_URL: String = "http://192.168.0.13:8080"
    const val USER_LOGIN_ENDPOINT: String = "/usuario/login"
    const val USER_SAVE_ENDPOINT: String = "/usuario/save"
    const val USER_REFRESH_TOKEN_ENDPOINT: String = "/usuario/refresh-token"
    const val USER_RESET_PASSWORD_ENDPOINT: String = "/usuario/reset-senha"
    const val USER_VERIFY_CODE_ENDPOINT: String = "/usuario/verificar-cod"
    const val USER_CHANGE_PASSWORD_ENDPOINT: String = "/usuario/alterar-senha"
    const val USER_GET_BY_ID: String = "/usuario/get-user"
    const val USER_TOKEN: String = "userToken"
    const val USER_LOCATION: String = "userLocation"
    const val USER_LAT: String = "userLong"
    const val USER_LONG: String = "userLat"
    const val PET_ENDPOINT: String = "/pet"
    const val MESSAGE_ENDPOINT: String = "/messages"

    const val PARAM_PET_ID = "petId"
    const val PARAM_USER_ID = "userId"
    const val PARAM_RECEIVER_ID = "receiverId"

}