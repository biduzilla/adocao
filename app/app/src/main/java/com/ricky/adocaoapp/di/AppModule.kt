package com.ricky.adocaoapp.di

import com.ricky.adocaoapp.data.network.api.PetApi
import com.ricky.adocaoapp.data.network.api.RefreshTokenAPI
import com.ricky.adocaoapp.data.network.api.UserAPI
import com.ricky.adocaoapp.data.network.interceptor.AuthInterceptor
import com.ricky.adocaoapp.data.repository.PetRepositoryImpl
import com.ricky.adocaoapp.data.repository.UserRepositoryImpl
import com.ricky.adocaoapp.domain.repository.PetRepository
import com.ricky.adocaoapp.domain.repository.UserRepository
import com.ricky.adocaoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserApi(authInterceptor: AuthInterceptor): UserAPI {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun providePetApi(authInterceptor: AuthInterceptor): PetApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PetApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRefreshToken(): RefreshTokenAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RefreshTokenAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        api: UserAPI,
        refreshTokenAPI: RefreshTokenAPI
    ): UserRepository = UserRepositoryImpl(api, refreshTokenAPI)

    @Singleton
    @Provides
    fun providePetRepository(
        api: PetApi
    ): PetRepository = PetRepositoryImpl(api)
}