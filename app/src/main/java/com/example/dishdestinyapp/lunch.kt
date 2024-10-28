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


class lunch : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    private val lunchItems = arrayOf(
        "Ghee Rice",
        "Chicken Curry",
        "Malabar Mutton Biriyani",
        "Mutton Soup"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lunch)

        listView = findViewById(R.id.listView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageButtona = findViewById<ImageButton>(R.id.imageButton1)
        val imageButtonIdlii = findViewById<ImageButton>(R.id.imageButton)
        val imageButtonKappaPuttu = findViewById<ImageButton>(R.id.imageButton2)
        val imageButtonPathiri = findViewById<ImageButton>(R.id.imageButton3)

        imageButtona.setOnClickListener {
            val intent = Intent(this, chicken::class.java)
            startActivity(intent)
        }

        imageButtonIdlii.setOnClickListener {
            val intent1 = Intent(this, ghee::class.java)
            startActivity(intent1)
        }

        imageButtonKappaPuttu.setOnClickListener {
            val intent2 = Intent(this, malabar::class.java)
            startActivity(intent2)
        }

        imageButtonPathiri.setOnClickListener {
            val intent3 = Intent(this, mutton::class.java)
            startActivity(intent3)
        }

        val backButton = findViewById<Button>(R.id.backButton)
        val searchView = findViewById<SearchView>(R.id.searchView)

        backButton.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lunchItems)
        listView.adapter = adapter

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                listView.visibility = View.VISIBLE
            } else {
                listView.visibility = View.GONE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchView.windowToken, 0)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val lowercaseQuery = query.lowercase()
                    val matchingItem = lunchItems.find { it.lowercase() == lowercaseQuery }

                    if (matchingItem != null) {
                        val intent = Intent(this@lunch, getDestinationActivity(matchingItem.lowercase()))
                        startActivity(intent)
                    } else {
                            Toast.makeText(this@lunch, "Item not available", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    adapter.filter.filter(newText.lowercase())
                    listView.visibility = if (newText.isNotEmpty()) View.VISIBLE else View.GONE
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

            if (selectedItem != null) {
                val intent = Intent(this@lunch, getDestinationActivity(selectedItem.lowercase())) // Use lowercase here
                startActivity(intent)
            } else {
                Toast.makeText(this@lunch, "Item not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDestinationActivity(itemName: String): Class<*> {
        return when (itemName.lowercase()) {
            "ghee rice" -> ghee::class.java
            "chicken curry" -> chicken::class.java
            "malabar mutton biriyani" -> malabar::class.java
            "mutton soup" -> mutton::class.java
            else -> throw IllegalArgumentException("No activity found for item: $itemName")
        }
    }
}