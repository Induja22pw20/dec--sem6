package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var checkBoxTerms: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Ensure this matches your layout file name

        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextAge = findViewById(R.id.editTextAge)
        checkBoxTerms = findViewById(R.id.checkBoxTerms)

        val buttonSave: Button = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            if (checkBoxTerms.isChecked) {
                // Handle save action
            } else {
                // Show a message that terms must be accepted
            }
        }
    }
}
