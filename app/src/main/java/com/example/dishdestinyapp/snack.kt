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


class snack : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    private val snackItems = arrayOf("Egg Cutlet", "Pazhamporie", "Unniyappam", "Vattappam")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_snack)

        listView = findViewById(R.id.listView1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageButtonEggCutlet = findViewById<ImageButton>(R.id.imageButton3)
        val imageButtonPazhamporie = findViewById<ImageButton>(R.id.imageButton)
        val imageButtonUnniyappam = findViewById<ImageButton>(R.id.imageButton2)
        val imageButtonVattappam = findViewById<ImageButton>(R.id.imageButton1)

        imageButtonEggCutlet.setOnClickListener {
            startActivity(Intent(this, eggcut::class.java))
        }

        imageButtonPazhamporie.setOnClickListener {
            startActivity(Intent(this, pazhamporie::class.java))
        }

        imageButtonUnniyappam.setOnClickListener {
            startActivity(Intent(this, unniyappam::class.java))
        }

        imageButtonVattappam.setOnClickListener {
            startActivity(Intent(this, vattayappam::class.java))
        }

        val backButton = findViewById<Button>(R.id.backButton)
        val searchView = findViewById<SearchView>(R.id.searchView)

        backButton.setOnClickListener {
            startActivity(Intent(this, menu::class.java))
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, snackItems)
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
                    val matchingItem = snackItems.find { item -> item.lowercase() == lowercaseQuery }
                    if (matchingItem != null) {
                        startActivity(Intent(this@snack, getDestinationActivity(lowercaseQuery)))
                    } else {
                        Toast.makeText(this@snack, "Item not available", Toast.LENGTH_SHORT).show()
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
            selectedItem?.let {
                searchView.setQuery(it, false)
                listView.visibility = View.GONE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchView.windowToken, 0)
                startActivity(Intent(this@snack, getDestinationActivity(it.lowercase())))
            }
        }
    }

    private fun getDestinationActivity(itemName: String): Class<*> {
        return when (itemName) {
            "egg cutlet" -> eggcut::class.java
            "pazhamporie" -> pazhamporie::class.java
            "unniyappam" -> unniyappam::class.java
            "vattappam" -> vattayappam::class.java
            else -> throw IllegalArgumentException("No activity found for item: $itemName")
        }
    }
}