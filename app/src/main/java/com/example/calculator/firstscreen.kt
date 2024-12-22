package com.example.calculator

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
fun firstscreen(navHostController: NavHostController, viewModel: SettingsViewModel = viewModel()) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var flag1 by remember { mutableStateOf(false) }
    var flag2 by remember { mutableStateOf(false) }
    var opr by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val soundEnabled by viewModel.soundEnabled.collectAsState()

    val mediaPlayer = remember { MediaPlayer.create(navHostController.context, R.raw.button_click) }

    fun allot(input: String) {
        if (!flag1) {
            if (input == "." && num1.contains(".")) return
            num1 += input
        } else if (flag1 && !flag2) { // Allow entering num2 after operator
            if (input == "." && num2.contains(".")) return
            num2 += input
        }

        if (soundEnabled) {
            mediaPlayer.start()
        }
    }

    fun Clear() {
        num1 = ""
        num2 = ""
        result = ""
    }

    fun execute() {
        val num1Double = num1.toDoubleOrNull() ?: 0.0
        val num2Double = num2.toDoubleOrNull() ?: 0.0
        result = when (opr) {
            "/" -> (num1Double / num2Double).toString()
            "*" -> (num1Double * num2Double).toString()
            "-" -> (num1Double - num2Double).toString()
            "+" -> (num1Double + num2Double).toString()
            else -> result
        }
        flag2 = true
        flag1 = true

        if (soundEnabled) {
            mediaPlayer.start()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CALCULATOR", fontSize = 18.sp) },
                actions = {
                    IconButton(onClick = { navHostController.navigate("secondscreen") }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { navHostController.navigate("thirdscreen") }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(paddingValues)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(num1, fontSize = 27.sp)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(opr, fontSize = 24.sp)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(num2, fontSize = 27.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(result, fontSize = 40.sp)
                }

                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            flag1 = false
                            flag2 = false
                            opr = ""
                            Clear()

                            if (soundEnabled) {
                                mediaPlayer.start()
                            }
                        }, modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text("AC")
                    }
                    Button(
                        onClick = {
                            execute()
                        }, modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text("C")
                    }
                    Button(
                        onClick = {
                            opr = ""
                            execute()

                            if (soundEnabled) {
                                mediaPlayer.start()
                            }
                        }, modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text(" ")
                    }
                    Button(
                        onClick = {
                            opr = "/"
                            flag1 = true
                            flag2 = false

                            if (soundEnabled) {
                                mediaPlayer.start()
                            }
                        }, modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text("/")
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { allot("7") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("7")
                    }
                    Button(onClick = { allot("8") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("8")
                    }
                    Button(onClick = { allot("9") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("9")
                    }
                    Button(
                        onClick = {
                            opr = "*"
                            flag1 = true
                            flag2 = false
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Text("*")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { allot("4") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("4")
                    }
                    Button(onClick = { allot("5") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("5")
                    }
                    Button(onClick = { allot("6") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("6")
                    }
                    Button(
                        onClick = {
                            opr = "-"
                            flag1 = true
                            flag2 = false
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Text("-")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { allot("1") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("1")
                    }
                    Button(onClick = { allot("2") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("2")
                    }
                    Button(onClick = { allot("3") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("3")
                    }
                    Button(
                        onClick = {
                            opr = "+"
                            flag1 = true
                            flag2 = false

                            if (soundEnabled) {
                                mediaPlayer.start()
                            }
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Text("+")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                        execute()
                        if (soundEnabled) {
                        mediaPlayer.start()
                    }}, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("%")
                    }
                    Button(onClick = { allot("0") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text("0")
                    }
                    Button(onClick = { allot(".") }, modifier = Modifier.weight(1f).padding(4.dp)) {
                        Text(".")
                    }
                    Button(
                        onClick = {
                            execute()
                            num1 = result
                            num2 = ""
                            opr = ""
                            result = ""
                        }, modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Text("=")
                    }
                }
            }
        }
    )
}
