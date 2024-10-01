package com.ricky.adocaoapp.presentation.home

import androidx.lifecycle.ViewModel
import com.ricky.adocaoapp.domain.use_case.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userManager: UserManager,
    ):ViewModel() {
}