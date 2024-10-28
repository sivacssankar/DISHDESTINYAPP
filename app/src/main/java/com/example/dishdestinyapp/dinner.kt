package com.example.dishdestinyapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import kotlin.jvm.java

class dinner : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    private val dinnerItems = arrayOf("Appam", "Chapati", "Dosa", "Sambar")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dinner)

        listView = findViewById(R.id.listView)

        // Apply window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set click listeners for image buttons
        val imageButtonAppam = findViewById<ImageButton>(R.id.imageButton2)
        val imageButtonIdli = findViewById<ImageButton>(R.id.imageButton1)
        val imageButtonKappaPuttu = findViewById<ImageButton>(R.id.imageButton)
        val imageButtonPathiri = findViewById<ImageButton>(R.id.imageButton3)

        imageButtonAppam.setOnClickListener { startActivity(Intent(this, Appam::class.java)) }
        imageButtonIdli.setOnClickListener { startActivity(Intent(this, sambar::class.java)) }
        imageButtonKappaPuttu.setOnClickListener { startActivity(Intent(this, chapati::class.java)) }
        imageButtonPathiri.setOnClickListener { startActivity(Intent(this, dosha::class.java)) }


        val backButton = findViewById<Button>(R.id.backButton)
        val searchView = findViewById<SearchView>(R.id.searchView)

        backButton.setOnClickListener { startActivity(Intent(this, menu::class.java)) }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dinnerItems)
        listView.adapter = adapter

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            listView.visibility = if (hasFocus) View.VISIBLE else View.GONE
            if (!hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchView.windowToken, 0)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val lowercaseQuery = it.lowercase()
                    val matchingItem = dinnerItems.find { item -> item.lowercase() == lowercaseQuery }
                    if (matchingItem != null) {
                        startActivity(Intent(this@dinner, getDestinationActivity(lowercaseQuery)))
                    } else {
                        Toast.makeText(this@dinner, "Item not available", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    adapter.filter.filter(it.lowercase())
                    listView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
                }
                return false
            }
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position)
            searchView.setQuery(selectedItem, false)
            listView.visibility = View.GONE
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(searchView.windowToken, 0)

            selectedItem?.let {
                startActivity(Intent(this@dinner, getDestinationActivity(it.lowercase())))
            } ?: run {
                Toast.makeText(this@dinner, "Item not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDestinationActivity(itemName: String): Class<*> {
        return when (itemName) {
            "appam" -> Appam::class.java
            "chapati" -> chapati::class.java
            "dosa" -> dosha::class.java
            "sambar" -> sambar::class.java
            else -> throw IllegalArgumentException("No activity found for item: $itemName")
        }
    }
}