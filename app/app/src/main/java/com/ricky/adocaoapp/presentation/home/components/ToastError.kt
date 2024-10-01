package com.ricky.adocaoapp.presentation.home.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastError(error: String, event: () -> Unit) {
    val context = LocalContext.current
    if (error.isNotBlank()) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        event()
    }
}