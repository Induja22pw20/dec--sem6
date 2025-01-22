package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var displayTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        displayTextView = findViewById(R.id.displayTextView)

        // Get data from Intent
        val firstName = intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")
        val Country = intent.getStringExtra("COUNTRY")
        val visitType = intent.getStringExtra("VISIT_TYPE")

        // Display the data
        displayTextView.text = "First Name: $firstName\nLast Name: $lastName\nVisit Type: $visitType\nCountry: $Country"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}