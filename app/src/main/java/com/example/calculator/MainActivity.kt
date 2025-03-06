package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }
    var operator by remember { mutableStateOf("+") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Avi's Calculator",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )


        OutlinedTextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("Enter first number", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Enter second number", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(20.dp))


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("+", "-", "*", "/").forEach { op ->
                Button(
                    onClick = {
                        operator = op
                        val num1 = input1.toDoubleOrNull() ?: 0.0
                        val num2 = input2.toDoubleOrNull() ?: 0.0
                        result = when (op) {
                            "+" -> (num1 + num2).toString()
                            "-" -> (num1 - num2).toString()
                            "*" -> (num1 * num2).toString()
                            "/" -> if (num2 != 0.0) (num1 / num2).toString() else "Error: Division by zero"
                            else -> "Invalid"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(op, fontSize = 18.sp, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Result: $result",
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}