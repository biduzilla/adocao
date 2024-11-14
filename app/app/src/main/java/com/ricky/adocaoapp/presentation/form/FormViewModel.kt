package com.ricky.adocaoapp.presentation.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.models.PetRequest
import com.ricky.adocaoapp.domain.use_case.PetManager
import com.ricky.adocaoapp.domain.use_case.UserManager
import com.ricky.adocaoapp.utils.Constants
import com.ricky.adocaoapp.utils.Resource
import com.ricky.adocaoapp.utils.bitmapToByteArray
import com.ricky.adocaoapp.utils.byteArrayToBitmap
import com.ricky.adocaoapp.utils.uriToBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val petManager: PetManager,
    savedStateHandle: SavedStateHandle,
    private val userManager: UserManager,
    private val dataStoreUtil: DataStoreUtil,
) : ViewModel() {

    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    init {
        getLoc()
        viewModelScope.launch {
            dataStoreUtil.getToken().collect { token ->
                token?.let {
                    _state.update { currentState ->
                        currentState.copy(
                            userId = token.idUser
                        )
                    }
                }
            }
        }
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            loadPet(petId)
        }

//        viewModelScope.launch {
//            dataStoreUtil.getToken().collect {
//                _state.update {
//                    it.copy(
//                        userId = it.userId
//                    )
//                }
//            }
//        }
    }

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.AddPet -> {
                if (_state.value.nome.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorNome = true
                        )
                    }
                    return
                }

                if (_state.value.descricao.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorDescricao = true
                        )
                    }
                    return
                }


                if (_state.value.foto == null) {
                    _state.update {
                        it.copy(
                            onErrorPhoto = true
                        )
                    }
                    return
                }

                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }

                    val pet = PetRequest(
                        nome = _state.value.nome,
                        idade = _state.value.idade,
                        localizacao = _state.value.cidade,
                        descricao = _state.value.descricao,
                        genero = _state.value.genero,
                        tipoAnimal = _state.value.especie,
                        foto = bitmapToByteArray(_state.value.foto!!),
                        status = _state.value.status,
                        tamanho = _state.value.tamanho,
                        donoId = _state.value.userId,
                        lat = _state.value.lat,
                        long = _state.value.long,
                    )
                    if (_state.value.isUpdate) {
                        pet.id = _state.value.petId

                        petManager.update(pet).onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            error = result.message ?: "Error Inesperado"
                                        )
                                    }
                                }

                                is Resource.Loading -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = true
                                        )
                                    }
                                }

                                is Resource.Success -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            isOk = true
                                        )
                                    }
                                }
                            }
                        }.launchIn(viewModelScope)
                    } else {
                        petManager.save(pet).onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            error = result.message ?: "Error Inesperado"
                                        )
                                    }
                                }

                                is Resource.Loading -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = true
                                        )
                                    }
                                }

                                is Resource.Success -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            isOk = true
                                        )
                                    }
                                }
                            }
                        }.launchIn(viewModelScope)
                    }
                }
            }

            FormEvent.DeletePet -> {
                if (!_state.value.isUpdate) {
                    return
                }

                petManager.deleteById(_state.value.petId).onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = result.message ?: "Error Inesperado"
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isOk = true
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }

            is FormEvent.OnChangeCidade -> {
                _state.update {
                    it.copy(
                        cidade = event.cidade
                    )
                }
            }

            is FormEvent.OnChangeDescricao -> _state.update {
                it.copy(
                    descricao = event.descricao,
                    onErrorDescricao = false
                )
            }

            is FormEvent.OnChangeEspecie -> {
                _state.update {
                    it.copy(
                        especie = event.especia
                    )
                }
            }

            is FormEvent.OnChangeGenero -> {
                _state.update {
                    it.copy(
                        genero = event.genero
                    )
                }
            }

            is FormEvent.OnChangeIdade -> {
                _state.update {
                    it.copy(
                        idade = event.idade
                    )
                }
            }

            is FormEvent.OnChangeNome -> {
                _state.update {
                    it.copy(
                        nome = event.nome,
                        onErrorNome = false
                    )
                }
            }

            is FormEvent.OnChangeStatus -> {
                _state.update {
                    it.copy(
                        status = event.status
                    )
                }
            }

            is FormEvent.OnChangeTamanho -> {
                _state.update {
                    it.copy(
                        tamanho = event.tamanho
                    )
                }
            }

            is FormEvent.SelectPhoto -> {
                event.uri?.let {
                    _state.update {
                        it.copy(
                            foto = uriToBitmap(uri = event.uri, context = event.context),
                            onErrorPhoto = false
                        )
                    }
                }
            }

            FormEvent.ShowBottomSheet -> {
                _state.update {
                    it.copy(
                        isShowBottomSheet = !_state.value.isShowBottomSheet
                    )
                }
            }

            FormEvent.ShowDialogRemover -> {
                _state.update {
                    it.copy(
                        isShowDialogRemover = !_state.value.isShowDialogRemover
                    )
                }
            }

            FormEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = ""
                    )
                }
            }
        }
    }

    private fun getLoc() {
        viewModelScope.launch {
            dataStoreUtil.getLat().collect { lat ->
                _state.update {
                    it.copy(
                        lat = lat,
                    )
                }
            }
        }
        viewModelScope.launch {
            dataStoreUtil.getLong().collect { long ->
                _state.update {
                    it.copy(
                        long = long,
                    )
                }

            }
        }
    }

    private fun loadPet(petId: String) {
        petManager.getById(petId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "Error Inesperado"
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Resource.Success -> {
                    result.data?.let {
                        _state.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                nome = it.nome,
                                descricao = it.descricao,
                                idade = it.idade,
                                cidade = it.localizacao,
                                genero = it.genero,
                                especie = it.tipoAnimal,
                                status = it.status,
                                tamanho = it.tamanho,
                                foto = byteArrayToBitmap(it.foto),
                                petId = it.id,
                                isUpdate = true,
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}