package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var resultTextView: TextView
    private lateinit var addButton: Button
    private lateinit var subtractButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var divideButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views correctly
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        resultTextView = findViewById(R.id.resultTextView)
        addButton = findViewById(R.id.addButton)
        subtractButton = findViewById(R.id.subtractButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        divideButton = findViewById(R.id.divideButton)

        // Set button click listeners
        addButton.setOnClickListener { performOperation("+") }
        subtractButton.setOnClickListener { performOperation("-") }
        multiplyButton.setOnClickListener { performOperation("*") }
        divideButton.setOnClickListener { performOperation("/") }
    }

    private fun performOperation(operator: String) {
        val input1Str = input1.text.toString()
        val input2Str = input2.text.toString()

        if (input1Str.isEmpty() || input2Str.isEmpty()) {
            resultTextView.text = getString(R.string.error_empty)
            return
        }

        val num1 = input1Str.toDoubleOrNull()
        val num2 = input2Str.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            resultTextView.text = getString(R.string.error_invalid)
            return
        }

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> Double.NaN
        }

        resultTextView.text = if (result.isNaN()) {
            getString(R.string.error_division_by_zero)
        } else {
            getString(R.string.result_prefix) + result.toString()
        }
    }
}
