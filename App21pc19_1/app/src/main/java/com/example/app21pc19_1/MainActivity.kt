package com.example.app21pc19_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val tv1 = findViewById<TextView>(R.id.button) as TextView
        val button1 = findViewById<Button>(R.id.button) as Button
        val img1 = findViewById<ImageView>(R.id.imageView2) as ImageView
        button1.setOnClickListener()
            {
                Toast.makeText(this, "NANDA", Toast.LENGTH_SHORT).show()
                tv1.text=getString(R.string.TextView)
                img1.setImageResource(R.drawable.two)
            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}