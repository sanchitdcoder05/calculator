package com.example.calculator

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
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
            val context = LocalContext.current
            Box(
                modifier = Modifier
                    .fillMaxSize() 
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(alignment = Alignment.TopStart)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Enable Button Sound", fontSize = 18.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = soundEnabled,
                            onCheckedChange = { viewModel.toggleSound(it) }
                        )
                    }
                }
                Row(modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(12.dp)
                ) {
                    ClickableText(
                        text = buildAnnotatedString {
                            append("Contact Me!")
                        },
                        onClick = {
                            val url = "mailto:sanchitdhabale95@gmail.com?subject=Reaching%20From%20Calculator%20App&body="
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                        style = androidx.compose.ui.text.TextStyle(color = Color.Red)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ClickableText(
                        text = buildAnnotatedString {
                            append("Buy me a coffee!")
                        },
                        onClick = {
                            val url = "https://www.youtube.com/watch?v=g3QdZQCF-7Q&list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbAVMg3QdZQCF-7Q&index=1"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                        style = androidx.compose.ui.text.TextStyle(color = Color.Red)
                    )
                }
            }
        }
    )
}
