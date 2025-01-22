package com.example.myapplication


import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var visitTypeSpinner: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var continueButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        firstNameEditText = findViewById(R.id.editTextText)
        lastNameEditText = findViewById(R.id.editTextText2)
        visitTypeSpinner = findViewById(R.id.spinner)
        radioGroup = findViewById(R.id.radioGroup)
        continueButton = findViewById(R.id.button5)
        continueButton.setOnClickListener {
            ()
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            if (!isValidName(firstName) || !isValidName(lastName)) {
                Toast.makeText(this, "Please enter valid names (alphabets only)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val Country = visitTypeSpinner.selectedItem.toString()
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val visitType = radioButton?.text.toString()
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("FIRST_NAME", firstName)
                putExtra("LAST_NAME", lastName)
                putExtra("COUNTRY", Country)
                putExtra("VISIT_TYPE", visitType)
            }
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun isValidName(name: String): Boolean {
        // Regular expression to match only alphabets (both uppercase and lowercase)
        return name.matches(Regex("^[a-zA-Z]+$"))
    }
}