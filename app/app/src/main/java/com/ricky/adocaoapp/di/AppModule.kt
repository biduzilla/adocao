package com.ricky.adocaoapp.di

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.data.network.api.ChatApi
import com.ricky.adocaoapp.data.network.api.PetApi
import com.ricky.adocaoapp.data.network.api.RefreshTokenAPI
import com.ricky.adocaoapp.data.network.api.UserAPI
import com.ricky.adocaoapp.data.network.interceptor.AuthInterceptor
import com.ricky.adocaoapp.data.network.websocket.GetMessage
import com.ricky.adocaoapp.data.network.websocket.SendMessage
import com.ricky.adocaoapp.data.repository.ChatRepositoryImpl
import com.ricky.adocaoapp.data.repository.PetRepositoryImpl
import com.ricky.adocaoapp.data.repository.TokenRepositoryImpl
import com.ricky.adocaoapp.data.repository.UserRepositoryImpl
import com.ricky.adocaoapp.domain.repository.ChatRepository
import com.ricky.adocaoapp.domain.repository.PetRepository
import com.ricky.adocaoapp.domain.repository.TokenRepository
import com.ricky.adocaoapp.domain.repository.UserRepository
import com.ricky.adocaoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.stomp.StompSession
import org.hildan.krossbow.websocket.ktor.KtorWebSocketClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWebsocketSession(): StompSession {
        return runBlocking(Dispatchers.IO) {
            val client = StompClient(KtorWebSocketClient())
            client.connect("ws://192.168.0.13:8080/ws")
        }
    }

    @Singleton
    @Provides
    fun provideSendMessage(session: StompSession): SendMessage {
        return SendMessage(session)
    }

    @Singleton
    @Provides
    fun provideGetMessage(session: StompSession): GetMessage {
        return GetMessage(session)
    }

    @Singleton
    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
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
    fun provideChatApi(authInterceptor: AuthInterceptor): ChatApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApi::class.java)
    }


    @Singleton
    @Provides
    fun provideUserRepository(
        api: UserAPI,
    ): UserRepository = UserRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideTokenRepository(
        api: RefreshTokenAPI,
    ): TokenRepository = TokenRepositoryImpl(api)

    @Singleton
    @Provides
    fun providePetRepository(
        api: PetApi
    ): PetRepository = PetRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideChatRepository(
        api: ChatApi
    ): ChatRepository = ChatRepositoryImpl(api)
}