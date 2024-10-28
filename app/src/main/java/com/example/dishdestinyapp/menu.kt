package com.example.dishdestinyapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mealTypeSpinner: Spinner = findViewById(R.id.mealTypeSpinner)
        val mealTypes = resources.getStringArray(R.array.meal_types).toList()
        val imageButton: ImageButton = findViewById(R.id.imageButton2)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mealTypes)
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        mealTypeSpinner.adapter = adapter
        mealTypeSpinner.prompt = getString(R.string.meal_type_hint)

        imageButton.setOnClickListener {
            val selectedMealType = mealTypeSpinner.selectedItem.toString()
            if (selectedMealType == "BREAKFAST") {
                val intent = Intent(this, breakfast::class.java)
                startActivity(intent)
            }
            else if(selectedMealType == "LUNCH"){
                val intent = Intent(this, lunch::class.java)
                startActivity(intent)
            }
            else if(selectedMealType == "SNACK"){
                val intent = Intent(this, snack::class.java)
                startActivity(intent)
            }
            else if(selectedMealType == "DINNER"){
                val intent = Intent(this, dinner::class.java)
                startActivity(intent)
            }

        }
    }
}