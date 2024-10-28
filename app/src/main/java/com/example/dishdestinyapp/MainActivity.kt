package com.example.dishdestinyapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handler section (runs in all cases)
        Handler(Looper.getMainLooper()).postDelayed({
            // Start menu activity
            startActivity(Intent(this, menu::class.java))

            // Check first launch
            if (isFirstTimeLaunch()) {
                // Start firsttime activity
                startActivity(Intent(this, firsttime::class.java))
            }

            // Finish MainActivity
            finish()
        }, 700)
    }

    private fun isFirstTimeLaunch(): Boolean {
        val sharedPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isFirstLaunch = !sharedPrefs.getBoolean("first_launch", false)

        if (isFirstLaunch) {
            // Set the flag to indicate first launch is complete
            sharedPrefs.edit().putBoolean("first_launch", true).apply()
        }

        return isFirstLaunch
    }
}