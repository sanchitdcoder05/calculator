package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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

@Composable
fun firstscreen(navHostController: NavHostController) {
    var num by remember { mutableStateOf("") }
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var flag1 by remember { mutableStateOf(false) }
    var flag2 by remember { mutableStateOf(false) }
    var opr by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }


    fun allot(num:Int) {
        if (flag1 == false){
            num1= num1.toString() + num.toString()
        }
        if (flag2 == true){
            num2= num2.toString() + num.toString()
        }
    }

    fun Clear(){
        num1= ""
        num2= ""
        result= ""
    }
    fun execute(){
        if (opr=="/"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double/num2Double).toString()
            flag2 = true
            flag1 = true

        }
        if (opr=="*"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double*num2Double).toString()
            flag2 = true
            flag1 = true
        }
        if (opr=="-"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double-num2Double).toString()
            flag2 = true
            flag1 = true
        }
        if (opr=="+"){
            var num1Double=num1.toDoubleOrNull() ?: 0.0
            var num2Double=num2.toDoubleOrNull() ?: 0.0
            result = (num1Double+num2Double).toString()
            flag2 = true
            flag1 = true
        }
    }


    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize().fillMaxWidth()
            .padding(15.dp)){
        //Row1 for Other Projects
        //Will Add Later Because I Don't Know Navigation As of Now
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Text(num1, fontSize =27.sp)
            Spacer(modifier = Modifier.width(15.dp))
            if (opr=="") {
                Text("")
            }
            else {
                Text(opr, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(num2, fontSize = 27.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Text(result, fontSize = 40.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Button(
                onClick = {navHostController.navigate("secondscreen")},
            ) {
                Text("Unit")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Row {
                Button(
                    onClick = {navHostController.navigate("thirdscreen")},
                ) {
                    Text("Currency")
                }
            }

        }
        //Row2
        Row (modifier = Modifier.fillMaxWidth()){
            //AC
            Button(
                onClick = {
                    flag1 = false
                    flag2 = false
                    opr = ""
                    Clear()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("AC")
            }
            //X
            Button(
                onClick = {
                    allot(0)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("0")
            }
            //Not Decided
            Button(
                onClick = {
                    opr="+"
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("+")
            }
            //./.
            Button(
                onClick = {
                    opr = "/"
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("./.")
            }
        }
        Row (modifier = Modifier.fillMaxWidth()){
            //7
            Button(
                onClick = {
                    allot(7)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("7")
            }
            //8
            Button(
                onClick = {
                    allot(8)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("8")
            }
            //9
            Button(
                onClick = {
                    allot(9)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("9")
            }

            Button(
                onClick = {
                    opr="*"
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("*")
            }
        }
        Row (modifier = Modifier.fillMaxWidth()){

            Button(
                onClick = {
                    allot(4)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("4")
            }

            Button(
                onClick = {
                    allot(5)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("5")
            }

            Button(
                onClick = {
                    allot(6)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("6")
            }

            Button(
                onClick = {
                    opr="-"
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("-")
            }
        }
        Row (modifier = Modifier.fillMaxWidth()){
            //7
            Button(
                onClick = {
                    allot(1)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("1")
            }
            //8
            Button(
                onClick = {
                    allot(2)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("2")
            }
            //9
            Button(
                onClick = {
                    allot(3)
                    execute()
                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("3")
            }
            //=
            Button(
                onClick = {
                    execute()
                    num1= ""
                    num2= ""
                    opr=""

                    if(opr == ""){
                        num1 = result
                        result = ""
                    }

                },modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("=")
            }
        }


    }
    Spacer(modifier = Modifier.height(92.dp))
}