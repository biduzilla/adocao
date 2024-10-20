package com.ricky.adocaoapp.presentation.main.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ricky.adocaoapp.navigation.BottomScreens

@Composable
fun BottomBar(
    navController: NavController
) {
    val items = listOf(
        BottomScreens.HomeScreen,
        BottomScreens.MeusPosts,
        BottomScreens.ConfigScreen,
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                selected = currentRoute
                    ?.split("/")
                    ?.get(0) == item.route,
                label = { Text(text = item.route) },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (currentRoute?.split("/")
                                ?.get(0) == item.route
                        ) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.route
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}