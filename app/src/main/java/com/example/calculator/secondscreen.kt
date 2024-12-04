package com.example.calculator


import android.annotation.SuppressLint
import android.view.WindowInsets
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlin.math.roundToInt
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.*
import androidx.compose.foundation.layout.statusBars




@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    var selectedItem by remember { mutableStateOf("Unit") }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFBBDEFB)), // Background color (light blue)
        containerColor = Color(0xFFBBDEFB) // Matches the background color
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.unit), // This is the VectorDrawable (unit.xml)
                    contentDescription = "Unit"
                )
            },
            label = { Text("Unit") },
            selected = selectedItem == "Unit",
            onClick = {
                selectedItem = "Unit"
                navHostController.navigate("home") // Replace with actual route
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black, // Icon color for selected item (black)
                selectedTextColor = Color.Black, // Text color for selected item (black)
                unselectedIconColor = Color.White, // Icon color for unselected items (white)
                unselectedTextColor = Color.White, // Text color for unselected items (white)
                indicatorColor = Color(0xFF87CEEB) // Highlight color for selected item (lighter blue)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.unit), // This is the VectorDrawable (unit.xml)
                    contentDescription = "Unit"
                )
            },
            label = { Text("Weight") },
            selected = selectedItem == "Weight",
            onClick = {
                selectedItem = "Weight"
                navHostController.navigate("utility") // Replace with actual route
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFF87CEEB)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.unit), // This is the VectorDrawable (unit.xml)
                    contentDescription = "Unit"
                )
            },
            label = { Text("Settings") },
            selected = selectedItem == "Settings",
            onClick = {
                selectedItem = "Settings"
                navHostController.navigate("settings") // Replace with actual route
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFF87CEEB)
            )
        )
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun secondscreen(navHostController: NavHostController) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navHostController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Button(
                onClick = { navHostController.navigate("firstscreen") },
                modifier = Modifier
                    .padding(top = 12.dp, start = 16.dp) // Add enough top padding to clear the notification bar
                    .align(Alignment.TopStart)
            ) {
                Text("Back >")
            }



            // Centered Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(16.dp))

                // Input Value Field
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = {
                        inputValue = it
                        convertUnits()
                    },
                    label = { Text("Enter Value") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Unit Selection
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Input Unit Dropdown
                    UnitDropdown(
                        selectedUnit = inputUnit,
                        onUnitSelected = { selectedUnit ->
                            inputUnit = selectedUnit
                            conversionFactor.value = when (selectedUnit) {
                                "Meters" -> 1.0
                                "Centimeters" -> 0.01
                                "Feet" -> 0.3048
                                "Millimeters" -> 0.001
                                else -> 1.0
                            }
                            convertUnits()
                        },
                        expanded = iExpanded,
                        onExpandedChange = { iExpanded = it }
                    )

                    // Output Unit Dropdown
                    UnitDropdown(
                        selectedUnit = outputUnit,
                        onUnitSelected = { selectedUnit ->
                            outputUnit = selectedUnit
                            oConversionFactor.value = when (selectedUnit) {
                                "Meters" -> 1.0
                                "Centimeters" -> 0.01
                                "Feet" -> 0.3048
                                "Millimeters" -> 0.001
                                else -> 1.0
                            }
                            convertUnits()
                        },
                        expanded = oExpanded,
                        onExpandedChange = { oExpanded = it }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Result Display
                Text("Result = $outputValue $outputUnit", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

@Composable
fun UnitDropdown(
    selectedUnit: String,
    onUnitSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Box {
        Button(onClick = { onExpandedChange(true) }) {
            Text(text = selectedUnit)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
            listOf("Meters", "Centimeters", "Feet", "Millimeters").forEach { unit ->
                DropdownMenuItem(
                    text = { Text(unit) },
                    onClick = {
                        onUnitSelected(unit)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}