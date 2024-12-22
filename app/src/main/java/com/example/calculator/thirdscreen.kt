package com.example.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun thirdscreen(navHostController: NavHostController, viewModel: SettingsViewModel = viewModel()) {
    // Observe the state of soundEnabled
    val soundEnabled by viewModel.soundEnabled.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate("firstscreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValues)
            ) {

                // Toggle Button for Sound
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Enable Button Sound", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(15.dp))
                    Switch(
                        checked = soundEnabled,
                        onCheckedChange = { viewModel.toggleSound(it) }  // Update the sound setting
                    )
                }
            }
        }
    )
}
