package com.example.myapplication2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fahrenheitEditText = findViewById<EditText>(R.id.editTextNumber)
        val celsiusEditText = findViewById<EditText>(R.id.editTextNumber2)
        val convertButton = findViewById<Button>(R.id.button)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        convertButton.setOnClickListener {
            val fahrenheitText = fahrenheitEditText.text.toString()
            val celsiusText = celsiusEditText.text.toString()

            if (fahrenheitText.isEmpty() && celsiusText.isEmpty()) {
                Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var result = ""

            if (fahrenheitText.isNotEmpty()) {
                val fahrenheit = fahrenheitText.toDoubleOrNull()
                if (fahrenheit != null) {
                    val celsius = (fahrenheit - 32) * 5 / 9
                    result = String.format("%.2f°F = %.2f°C", fahrenheit, celsius)
                    Toast.makeText(this, "Result: $fahrenheit°F = %.2f°C", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid Fahrenheit value", Toast.LENGTH_SHORT).show()
                }
            }

            if (celsiusText.isNotEmpty()) {
                val celsius = celsiusText.toDoubleOrNull()
                if (celsius != null) {
                    val fahrenheit = (celsius * 9 / 5) + 32
                    result = String.format("%.2f°C = %.2f°F", celsius, fahrenheit)
                    Toast.makeText(this, "Result: $celsius°C = %.2f°F".format(fahrenheit), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Invalid Celsius value", Toast.LENGTH_SHORT).show()
                }
            }

            resultTextView.text = result
            resultTextView.visibility = View.VISIBLE
            convertButton.visibility = View.GONE
        }
    }
}
