package com.ricky.adocaoapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.navigation.AppNav
import com.ricky.adocaoapp.presentation.LocationViewModel
import com.ricky.adocaoapp.ui.theme.AdocaoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var dataStoreUtil: DataStoreUtil
    private val viewModel: LocationViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStoreUtil = DataStoreUtil(applicationContext)
        var darkMode by mutableStateOf(false)

        lifecycleScope.launch {
            dataStoreUtil.getTheme().collect{
                darkMode = it
            }
        }

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadLoc()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            AdocaoAppTheme(darkTheme = darkMode) {
                AppNav()
            }
        }
    }
}
