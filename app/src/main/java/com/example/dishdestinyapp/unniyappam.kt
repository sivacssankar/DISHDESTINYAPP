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

class unniyappam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_unniyappam)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val youtubeLink = findViewById<TextView>(R.id.youtubeLink)
        val buttonSetText = findViewById<ImageButton>(R.id.button)
        val textView = findViewById<TextView>(R.id.text)

        buttonSetText.setOnClickListener {
            textView.text = "Rice Flour: If you don't have time to soak raw rice, you can use rice flour directly."
        }

        youtubeLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Wukx5113rS8?si=lyG-untkXNJlbmtn"))
            startActivity(intent)
        }
    }
}