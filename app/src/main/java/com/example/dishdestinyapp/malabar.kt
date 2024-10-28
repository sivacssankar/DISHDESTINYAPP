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

class malabar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_malabar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val youtubeLink = findViewById<TextView>(R.id.youtubeLink)
        val buttonSetText = findViewById<ImageButton>(R.id.button)
        val textView = findViewById<TextView>(R.id.text)

        buttonSetText.setOnClickListener {
            textView.text = "Butter can be used as a substitute for ghee, offering a similar richness but with a milder flavor.\nAlmonds can provide a slightly different texture and flavor compared to cashew nuts.\nDried cranberries add a tart and sweet flavor to the biryani."
        }

        youtubeLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/yufaWlGc46c?si=B8Qju9P-H8dWBp-K"))
            startActivity(intent)
        }
    }
}