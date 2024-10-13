package com.ricky.adocaoapp.presentation.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climacompose.domain.location.LocationTracker
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.Usuario
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
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val userManager: UserManager,
    private val petManager: PetManager,
    savedStateHandle: SavedStateHandle,
    private val dataStoreUtil: DataStoreUtil,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    init {
        getLoc()
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            loadPet(petId)
        } ?: run {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
        }

        viewModelScope.launch {
            dataStoreUtil.getToken().collect {
                _state.update {
                    it.copy(
                        userId = it.userId
                    )
                }
            }
        }
    }

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.AddPet -> {
                if (_state.value.nome.trim().isBlank()) {
                    _state.value = FormState(
                        onErrorNome = true
                    )
                    return
                }

                if (_state.value.descricao.trim().isBlank()) {
                    _state.value = FormState(
                        onErrorDescricao = true
                    )
                    return
                }
                if (_state.value.microChip.trim().isBlank()) {
                    _state.value = FormState(
                        onErrorMicroChip = true
                    )
                    return
                }


                if (_state.value.foto == null) {
                    _state.value = _state.value.copy(
                        onErrorPhoto = true
                    )
                    return
                }

                val pet = Pet(
                    nome = _state.value.nome,
                    idade = _state.value.idade,
                    localizacao = _state.value.cidade,
                    usuario = Usuario(id = _state.value.userId),
                    descricao = _state.value.descricao,
                    genero = _state.value.genero,
                    tipoAnimal = _state.value.especie,
                    foto = bitmapToByteArray(_state.value.foto!!),
                    dataPublicacao = LocalDate.now(),
                    status = _state.value.status,
                    tamanho = _state.value.tamanho,
                    donoId = _state.value.userId,
                    microChip = _state.value.microChip,
                    lat = _state.value.lat,
                    long = _state.value.long,
                )

                if (_state.value.isUpdate) {
                    pet.id = _state.value.petId

                    petManager.update(pet).onEach { result ->
                        when (result) {
                            is Resource.Error -> {
                                _state.value = FormState(
                                    isLoading = false,
                                    error = result.message ?: "Error Inesperado"
                                )
                            }

                            is Resource.Loading -> {
                                _state.value = FormState(
                                    isLoading = true
                                )
                            }

                            is Resource.Success -> {
                                _state.value = FormState(
                                    isLoading = false,
                                    isOk = true
                                )
                            }
                        }
                    }
                }
                petManager.save(pet).onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = FormState(
                                isLoading = false,
                                error = result.message ?: "Error Inesperado"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = FormState(
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _state.value = FormState(
                                isLoading = false,
                                isOk = true
                            )
                        }
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
                            _state.value = FormState(
                                isLoading = false,
                                error = result.message ?: "Error Inesperado"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = FormState(
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _state.value = FormState(
                                isLoading = false,
                                isOk = true
                            )
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
                    _state.value = _state.value.copy(
                        foto = uriToBitmap(uri = event.uri, context = event.context),
                        onErrorPhoto = false
                    )
                } ?: run {
                    _state.value = _state.value.copy(
                        onErrorPhoto = true
                    )
                }
            }

            FormEvent.ShowBottomSheet -> {
                _state.value = _state.value.copy(
                    isShowBottomSheet = !_state.value.isShowBottomSheet
                )
            }

            FormEvent.ShowDialogRemover -> {
                _state.value = _state.value.copy(
                    isShowDialogRemover = !_state.value.isShowDialogRemover
                )
            }

            is FormEvent.OnChangeMicroChip -> {
                _state.value = _state.value.copy(
                    microChip = event.chip,
                    onErrorMicroChip = false
                )
            }
        }
    }

    private fun getLoc() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                _state.update {
                    it.copy(
                        lat = location.latitude,
                        long = location.longitude
                    )
                }
            } ?: kotlin.run {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Não consegui receber sua posição atual. Tenha certeza que a permissão de acessar a localização está garantida"
                    )
                }
            }
        }

    }

    private fun loadPet(petId: String) {
        petManager.getById(petId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = FormState(
                        isLoading = false,
                        error = result.message ?: "Error Inesperado"
                    )
                }

                is Resource.Loading -> {
                    _state.value = FormState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    result.data?.let {
                        _state.value = FormState(
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
        }.launchIn(viewModelScope)
    }
}