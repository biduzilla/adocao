package com.ricky.adocaoapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.domain.use_case.UserManager
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetById
import com.ricky.adocaoapp.utils.Constants
import com.ricky.adocaoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val petCaseGetById: PetCaseGetById,
    private val userManager: UserManager,
    private val saveStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    init {
        saveStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            loadPet(petId)
        }
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.ClearError -> {
                _state.value = DetailsState(
                    error = ""
                )
            }
        }
    }

    private fun loadPet(petId: String) {
        petCaseGetById(petId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = DetailsState(
                        isLoading = false,
                        error = result.message ?: "Error Inesperado"
                    )
                }

                is Resource.Loading -> {
                    _state.value = DetailsState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    result.data?.let {
                        _state.value = DetailsState(
                            isLoading = false,
                            pet = result.data,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}