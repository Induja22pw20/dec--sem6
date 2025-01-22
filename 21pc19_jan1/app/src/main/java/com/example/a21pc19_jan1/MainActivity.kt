package com.example.a21pc19_jan1

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var formatTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        formatTextView = findViewById(R.id.format_text_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        formatTextView.setOnClickListener { showPopupMenu(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.color_red -> findViewById<View>(R.id.main).setBackgroundColor(Color.RED)
            R.id.color_green -> findViewById<View>(R.id.main).setBackgroundColor(Color.GREEN)
            R.id.color_blue -> findViewById<View>(R.id.main).setBackgroundColor(Color.BLUE)
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menu.add("Small")
        popupMenu.menu.add("Medium")
        popupMenu.menu.add("Large")
        popupMenu.menu.add("Change text color")

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.title) {
                "Small" -> formatTextView.textSize = resources.getDimension(R.dimen.text_size_small)
                "Medium" -> formatTextView.textSize = resources.getDimension(R.dimen.text_size_medium)
                "Large" -> formatTextView.textSize = resources.getDimension(R.dimen.text_size_large)
                "Change text color" -> changeTextColor()
            }
            true
        }
        popupMenu.show()
    }

    private fun changeTextColor() {
        val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN)
        formatTextView.setTextColor(colors.random())
    }
}
