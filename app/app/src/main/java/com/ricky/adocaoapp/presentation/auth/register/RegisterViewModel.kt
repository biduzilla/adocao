package com.ricky.adocaoapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import com.ricky.adocaoapp.domain.use_case.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userCases: UserManager) : ViewModel() {
}