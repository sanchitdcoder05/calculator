package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import kotlin.math.roundToInt

@Composable
fun secondscreen(navHostController: NavHostController){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }




    fun convertUnits(){
        // ?:   is elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?:0.0

        val result = (inputValueDouble * conversionFactor.value * 100/oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
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
    ) {

        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        },
            label = { Text("Enter Value") })
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box{
                Button(onClick = { iExpanded = true},) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }


                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded=false}) {
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box{
                Button(onClick = {oExpanded = true},) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded=false}) {
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result = $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}}