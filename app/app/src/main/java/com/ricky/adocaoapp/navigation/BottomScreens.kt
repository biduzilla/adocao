package com.ricky.adocaoapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.PermMedia
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreens(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object HomeScreen : BottomScreens(
        route = "Home",
        selectedIcon = Icons.Filled.Pets,
        unselectedIcon = Icons.Outlined.Pets
    )

    data object MeusPosts : BottomScreens(
        route = "Meus Posts",
        selectedIcon = Icons.Filled.PermMedia,
        unselectedIcon = Icons.Outlined.PermMedia
    )

    data object ChatScreen : BottomScreens(
        route = "Chat",
        selectedIcon = Icons.AutoMirrored.Filled.Chat,
        unselectedIcon = Icons.AutoMirrored.Outlined.Chat
    )

    data object ConfigScreen : BottomScreens(
        route = "Config",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

}