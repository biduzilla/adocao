package com.ricky.adocaoapp.data.network.api

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PetApi {

    @GET(Constants.PET_ENDPOINT)
    suspend fun getAll(
        @Query("page") page: Int = 0,
        @Query("search") search: String?,
        @Query("orderBy") orderBy: String?,
        @Query("qtd") qtd: Int = 15, @Body filtros: FiltroSearch
    ): Response<Pet>

    @GET("${Constants.PET_ENDPOINT}/{id}")
    suspend fun getById(@Path("id")id:String):Response<Pet>

    @POST(Constants.PET_ENDPOINT)
    suspend fun save(@Body pet:Pet):Response<Pet>

    @PUT(Constants.PET_ENDPOINT)
    suspend fun update(@Body pet:Pet):Response<Pet>

    @DELETE("${Constants.PET_ENDPOINT}/{id}")
    suspend fun deleteById(@Path("id")id:String):Response<Void>
}