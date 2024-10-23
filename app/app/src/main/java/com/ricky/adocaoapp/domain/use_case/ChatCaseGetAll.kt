package com.ricky.adocaoapp.domain.use_case

import com.google.gson.Gson
import com.ricky.adocaoapp.domain.models.ChatMessage
import com.ricky.adocaoapp.domain.models.ErrorRequest
import com.ricky.adocaoapp.domain.repository.ChatRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChatCaseGetAll @Inject constructor(private val repository: ChatRepository) {
    operator fun invoke(
        senderId: String,
        recipientId: String
    ): Flow<Resource<List<ChatMessage>>> = flow {
        try {
            emit(Resource.Loading())

            repository.getMessages(
                senderId = senderId,
                recipientId = recipientId
            ).let { result ->
                if (result.isSuccessful) {
                    result.body()?.let { msgs ->
                        emit(Resource.Success(msgs))
                    } ?: run {
                        emit(Resource.Error("Error inesperado"))
                    }
                } else {
                    val error =
                        Gson().fromJson(result.errorBody()?.charStream(), ErrorRequest::class.java)
                    emit(Resource.Error(error?.message ?: "Error desconhecido"))
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}