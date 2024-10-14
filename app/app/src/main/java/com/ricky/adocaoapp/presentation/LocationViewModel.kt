package com.ricky.adocaoapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val locationTracker: LocationTracker,
) : ViewModel() {
    fun loadLoc() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                viewModelScope.launch {
                    dataStoreUtil.saveLat(location.latitude)
                    dataStoreUtil.saveLong(location.longitude)
                }
            } ?: kotlin.run {
                Log.i(
                    "infoteste",
                    "loadLoc: Não consegui receber sua posição atual. Tenha certeza que a permissão de acessar a localização está garantida"
                )
            }
        }
    }

}