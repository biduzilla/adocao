package com.ricky.adocaoapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreens(
    val route:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector
) {
    data object HomeScreen:BottomScreens(
        route="home",
        selectedIcon = Icons.Filled.Pets,
        unselectedIcon = Icons.Outlined.Pets
    )

    data object MsgScreen:BottomScreens(
        route="msg",
        selectedIcon = Icons.AutoMirrored.Filled.Chat,
        unselectedIcon = Icons.AutoMirrored.Outlined.Chat
    )

    data object ConfigScreen:BottomScreens(
        route="config",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

}