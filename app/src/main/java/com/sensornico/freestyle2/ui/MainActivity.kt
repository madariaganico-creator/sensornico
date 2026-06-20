package com.sensornico.freestyle2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sensornico.freestyle2.ui.screens.*
import com.sensornico.freestyle2.ui.theme.SensornicoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensornicoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    
    when (currentScreen) {
        Screen.HOME -> BottomNavLayout(onScreenChange = { currentScreen = it })
        Screen.DASHBOARD -> DashboardScreen()
        Screen.HISTORY -> HistoryScreen(onBack = { currentScreen = Screen.HOME })
        Screen.ALERTS -> AlertsScreen(onBack = { currentScreen = Screen.HOME })
        Screen.EXPORT -> ExportScreen(onBack = { currentScreen = Screen.HOME })
        Screen.SETTINGS -> SettingsScreen(onBack = { currentScreen = Screen.HOME })
    }
}

@Composable
fun BottomNavLayout(onScreenChange: (Screen) -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Dashboard, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") },
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                        onScreenChange(Screen.DASHBOARD)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.History, contentDescription = "Historial") },
                    label = { Text("Historial") },
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                        onScreenChange(Screen.HISTORY)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Notifications, contentDescription = "Alertas") },
                    label = { Text("Alertas") },
                    selected = selectedTab == 2,
                    onClick = {
                        selectedTab = 2
                        onScreenChange(Screen.ALERTS)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Download, contentDescription = "Exportar") },
                    label = { Text("Exportar") },
                    selected = selectedTab == 3,
                    onClick = {
                        selectedTab = 3
                        onScreenChange(Screen.EXPORT)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Configuración") },
                    label = { Text("Config") },
                    selected = selectedTab == 4,
                    onClick = {
                        selectedTab = 4
                        onScreenChange(Screen.SETTINGS)
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> DashboardScreen()
                1 -> HistoryScreen(onBack = { })
                2 -> AlertsScreen(onBack = { })
                3 -> ExportScreen(onBack = { })
                4 -> SettingsScreen(onBack = { })
            }
        }
    }
}

enum class Screen {
    HOME,
    DASHBOARD,
    HISTORY,
    ALERTS,
    EXPORT,
    SETTINGS
}
