package com.ricky.adocaoapp.domain.use_case.user

import android.util.Log
import com.google.gson.Gson
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.models.ErrorRequest
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.repository.UserRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UseRefreshToken @Inject constructor(
    private val repository: UserRepository,
    private val dataStoreUtil: DataStoreUtil
) {

    suspend operator fun invoke(): Token? {
        var refreshedToken: Token? = null

        dataStoreUtil.getToken().collect { storedToken ->
            storedToken?.let {
                try {
                    val result = repository.refreshToken(it)

                    if (result.isSuccessful) {
                        refreshedToken = result.body()
                    }
                } catch (e: Exception) {
                    Log.e("infoteste", e.localizedMessage ?: "Erro inesperado")
                }
            }
        }

        return refreshedToken
    }


}