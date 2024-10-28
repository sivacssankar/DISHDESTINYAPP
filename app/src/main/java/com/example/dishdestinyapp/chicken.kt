package com.example.dishdestinyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class chicken : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chicken)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val youtubeLink = findViewById<TextView>(R.id.youtubeLink)
        val buttonSetText = findViewById<ImageButton>(R.id.button)
        val textView = findViewById<TextView>(R.id.text)

        buttonSetText.setOnClickListener {
            textView.text = "You can substitute green chilies with other types of chilies, such as serrano peppers or jalapeños, for varying levels of spiciness"
        }

        youtubeLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/ADBVt66NXvw?si=ez-EXFOOH277Ozev"))
            startActivity(intent)
        }
    }
}