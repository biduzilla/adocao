package com.ricky.adocaoapp.data.network.api

import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.models.VerificarCod
import com.ricky.adocaoapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserAPI {

    @POST(Constants.USER_LOGIN_ENDPOINT)
    suspend fun login(@Body login: Login): Response<Token>

    @POST(Constants.USER_SAVE_ENDPOINT)
    suspend fun save(@Body usuario: Usuario): Response<Usuario>


    @POST("${Constants.USER_RESET_PASSWORD_ENDPOINT}/{email}")
    suspend fun resetPassword(@Path("email") email: String): Response<Void>

    @GET(Constants.USER_VERIFY_CODE_ENDPOINT)
    suspend fun verifyCod(@Body verificarCod: VerificarCod): Response<Void>

    @PUT(Constants.USER_CHANGE_PASSWORD_ENDPOINT)
    suspend fun changePassword(
        @Body resetSenha: ResetSenha,
    ): Response<Void>

    @GET("${Constants.USER_GET_BY_ID}/{idUser}")
    suspend fun getById(
        @Path("idUser") idUser:String
    ): Response<Usuario>

}