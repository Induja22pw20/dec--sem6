package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.red -> {
                window.decorView.setBackgroundColor(resources.getColor(R.color.red))
                true
            }
            R.id.green -> {
                window.decorView.setBackgroundColor(resources.getColor(R.color.green))
                true
            }
            R.id.blue -> {
                window.decorView.setBackgroundColor(resources.getColor(R.color.blue))
                true
            }
            R.id.yellow -> { // Handle Yellow Option
                window.decorView.setBackgroundColor(resources.getColor(R.color.yellow))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}