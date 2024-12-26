package com.example.calculator

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
        if (opr=="") {
            if (input == "." && num1.contains(".")) return
            num1 += input

        }
        else{
            if (input == "." && num2.contains(".")) return
            num2 += input
        }
//        else if (flag1 && !flag2) {
//            if (input == "." && num2.contains(".")) return
//            num2 += input
//        }

        if (soundEnabled) {
            mediaPlayer.start()
        }
    }

    fun Clear() {
        num1 = ""
        num2 = ""
        opr = ""
        result = ""
    }


    fun execute(){
        if (opr=="/"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double/num2Double).toString()
            flag2 = true
            flag1 = true

        }
        else if (opr=="*"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double*num2Double).toString()
            flag2 = true
            flag1 = true
        }
        else if (opr=="-"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double-num2Double).toString()
            flag2 = true
            flag1 = true
        }
        else if (opr=="+"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double+num2Double).toString()
            flag2 = true
            flag1 = true
        }
        if (soundEnabled) {
            mediaPlayer.start()
        }
    }

    fun c1() {
        if (num2 != "") {
            num2 = num2.dropLast(1)
        } else if (opr == "") {
            if (num1.isNotEmpty()) {
                num1 = num1.dropLast(1)
            }
        } else if (num2 == "") {
            opr = ""
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CALCULATOR", fontSize = 18.sp) },
                actions = {
                    IconButton(onClick = { navHostController.navigate("secondscreen") }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_currency_exchange_24),
                            modifier = Modifier.size(20.dp), contentDescription = "Currency")
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
                    .padding(15.dp)
                    .padding(paddingValues)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(num1, fontSize = 40.sp)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(opr, fontSize = 35.sp)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(num2, fontSize = 40.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(result, fontSize = 50.sp)
                }

                Divider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { Clear() ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("AC")
                    }
                    Button(onClick = { c1(); execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("C")

                    }
                    Button(onClick = { opr = ""; execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("+/-")
                    }
                    Button(onClick = { opr = "/"; flag1 = true; flag2 = false; execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("÷")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { allot("7") ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("7")
                    }
                    Button(onClick = { allot("8") ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("8")
                    }
                    Button(onClick = { allot("9") ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("9")
                    }
                    Button(onClick = { opr = "*" ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("×")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { allot("4") ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("4")
                    }
                    Button(onClick = { allot("5"); execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("5")
                    }
                    Button(onClick = { allot("6") ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("6")
                    }
                    Button(onClick = { opr = "-" ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("-")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { allot("1"); execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("1")
                    }
                    Button(onClick = { allot("2"); execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("2")
                    }
                    Button(onClick = { allot("3"); execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("3")
                    }
                    Button(onClick = { opr = "+" ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("+")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { execute(); execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("%")
                    }
                    Button(onClick = { allot("0"); execute() },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("0")
                    }
                    Button(onClick = { allot(".") ; execute()},
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text(".")
                    }
                    Button(onClick = {
                        execute();
                        num1 = result;
                        num2 = ""; opr = ""; result = ""
                                     },
                        modifier = Modifier.weight(1f).padding(4.dp).size(60.dp), shape = CircleShape) {
                        Text("=")
                    }
                }
            }
        }
    )
}
