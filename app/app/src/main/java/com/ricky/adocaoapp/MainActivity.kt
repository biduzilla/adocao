package com.ricky.adocaoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ricky.adocaoapp.navigation.AppNav
import com.ricky.adocaoapp.ui.theme.AdocaoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdocaoAppTheme(darkTheme = false) {
                AppNav()
            }
        }
    }
}
