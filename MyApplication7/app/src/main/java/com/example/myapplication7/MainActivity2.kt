package com.example.myapplication7

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val displayText =findViewById<TextView>(R.id.displaytextView5)
        val firstName =intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")
        val country = intent.getStringExtra("COUNTRY")
        val visitType = intent.getStringExtra("VISIT_TYPE")
        displayText.text ="FIRST NAME: $firstName\nLAST NAME: $lastName\nCountry: $country\nVisitType: $visitType"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}