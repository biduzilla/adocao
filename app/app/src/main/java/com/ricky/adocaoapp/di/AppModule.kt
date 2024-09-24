package com.ricky.adocaoapp.di

import com.ricky.adocaoapp.data.network.api.UserAPI
import com.ricky.adocaoapp.data.repository.UserRepositoryImpl
import com.ricky.adocaoapp.domain.repository.UserRepository
import com.ricky.adocaoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun proviteUserApi(): UserAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        api: UserAPI
    ): UserRepository = UserRepositoryImpl(api)
}