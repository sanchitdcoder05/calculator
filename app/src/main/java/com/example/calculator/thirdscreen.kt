package com.example.calculator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun thirdscreen(navHostController: NavHostController) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputCurrency by remember { mutableStateOf("INR") }
    var outputCurrency by remember { mutableStateOf("INR") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }


    fun convertCurrency() {
        if (inputCurrency=="INR"){
            if (outputCurrency=="INR"){
                conversionFactor.value=1.0
            }
            if(outputCurrency=="USD"){
                conversionFactor.value= 0.0119
            }
            if(outputCurrency=="Pounds"){
                conversionFactor.value= 0.0092
            }
            if(outputCurrency=="Euros"){
                conversionFactor.value= 0.011
            }
        }
        if (inputCurrency=="USD"){
            if (outputCurrency=="INR"){
                conversionFactor.value= 84.1003
            }
            if(outputCurrency=="USD"){
                conversionFactor.value=1.0
            }
            if(outputCurrency=="Pounds"){
                conversionFactor.value= 0.7714
            }
            if(outputCurrency=="Euros"){
                conversionFactor.value= 0.9261
            }
        }
        if (inputCurrency=="Pounds"){
            if (outputCurrency=="INR"){
                conversionFactor.value= 109.0211
            }
            if(outputCurrency=="USD"){
                conversionFactor.value= 1.2963
            }
            if(outputCurrency=="Pounds"){
                conversionFactor.value= 1.0
            }
            if(outputCurrency=="Euros"){
                conversionFactor.value= 1.2006
            }
        }
        if (inputCurrency=="Euros"){
            if (outputCurrency=="INR"){
                conversionFactor.value= 90.8064
            }
            if(outputCurrency=="USD"){
                conversionFactor.value=1.0797
            }
            if(outputCurrency=="Pounds"){
                conversionFactor.value= 0.8329
            }
            if(outputCurrency=="Euros"){
                conversionFactor.value= 1.0
            }
        }
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        outputValue= (inputValueDouble * conversionFactor.value).toString()

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp), // Ensures proper spacing from the top
        verticalArrangement = Arrangement.Top, // Adjust alignment to top after padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navHostController.navigate("firstscreen") },
            modifier = Modifier.align(Alignment.Start) // Aligns the button to the left for better UI
        ) {
            Text("Back >")
        }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Currency Converter")

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Box{

                Button(onClick = { iExpanded = true }) {
                    Text(text = inputCurrency)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("INR") },
                        onClick = {
                            iExpanded = false
                            inputCurrency = "INR"
                            convertCurrency()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("USD") },
                        onClick = {
                            iExpanded = false
                            inputCurrency = "USD"
                            convertCurrency()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pounds") },
                        onClick = {
                            iExpanded = false
                            inputCurrency = "Pounds"
                            convertCurrency()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Euros") },
                        onClick = {
                            iExpanded = false
                            inputCurrency = "Euros"
                            convertCurrency()
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.width(12.dp))

            OutlinedTextField(value = inputValue , onValueChange ={
                inputValue =it
                convertCurrency()
            },
                label = { Text("Enter Amount") }
            )

        }
        Spacer(modifier = Modifier.height(30.dp))
        Text("To")
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Box{

                Button(onClick = { oExpanded = true }) {
                    Text(text = outputCurrency)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("INR") },
                        onClick = {
                            oExpanded = false
                            outputCurrency = "INR"
                            convertCurrency()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("USD") },
                        onClick = {
                            oExpanded = false
                            outputCurrency = "USD"
                            convertCurrency()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pounds") },
                        onClick = {
                            oExpanded = false
                            outputCurrency = "Pounds"
                            convertCurrency()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Euros") },
                        onClick = {
                            oExpanded = false
                            outputCurrency = "Euros"
                            convertCurrency()
                        }
                    )
                }
            }



            Spacer(modifier = Modifier.width(12.dp))
            OutlinedTextField(value = outputValue, onValueChange = {
                convertCurrency()
            })
        }
    }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val current = LocalDateTime.now().format(formatter)
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom) {
        Text("Last Updated on $current IST")
        Spacer(modifier = Modifier.height(12.dp))
    }}}