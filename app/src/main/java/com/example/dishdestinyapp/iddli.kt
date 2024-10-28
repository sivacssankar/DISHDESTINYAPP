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

class iddli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iddli)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val youtubeLink = findViewById<TextView>(R.id.youtubeLink)
        val buttonSetText = findViewById<ImageButton>(R.id.button)
        val textView = findViewById<TextView>(R.id.text)

        buttonSetText.setOnClickListener {
            textView.text = "Poha (Flattened Rice)\nreplace about 25-50% of the rice with poha"
        }

        youtubeLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/v9Kq2kQSjJ0?si=Pl1nvUi1JWU3sZ7H"))
            startActivity(intent)
        }
    }
}
