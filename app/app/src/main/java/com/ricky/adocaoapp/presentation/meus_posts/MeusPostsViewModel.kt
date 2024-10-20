package com.ricky.adocaoapp.presentation.meus_posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.use_case.PetManager
import com.ricky.adocaoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeusPostsViewModel @Inject constructor(
    private val petManager: PetManager,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {

    private val _state = MutableStateFlow(MeusPostsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getToken().collect { token ->
                token?.let {
                    _state.update {
                        it.copy(
                            idUser = token.idUser
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event:MeusPostsEvent){
        when(event){
            MeusPostsEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = ""
                    )
                }
            }
            MeusPostsEvent.OnLoadMore -> {
                _state.update {
                    it.copy(
                        page = _state.value.page + 1
                    )
                }
                loadMore()
            }
            MeusPostsEvent.Resume -> {
                _state.update {
                    it.copy(
                        page = 0,
                        pets = emptyList()
                    )
                }
                loadMore()
            }
        }
    }

    private fun loadMore() {
        petManager.getByUserId(
            page = _state.value.page,
            userId = _state.value.idUser,
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Error"
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                    )
                }

                is Resource.Success -> {
                    result.data?.let { pagePet ->
                        _state.update {
                            val newPetsList = _state.value.pets + pagePet.content
                            it.copy(
                                pets = newPetsList,
                                loadMoreVisible = newPetsList.size < pagePet.totalElements,
                                isLoading = false,
                            )
                        }
                    }

                }
            }
        }.launchIn(viewModelScope)
    }

}