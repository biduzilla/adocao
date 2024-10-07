package com.ricky.adocaoapp.presentation.details

import androidx.lifecycle.ViewModel
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val petCaseGetById: PetCaseGetById) :
    ViewModel() {
}