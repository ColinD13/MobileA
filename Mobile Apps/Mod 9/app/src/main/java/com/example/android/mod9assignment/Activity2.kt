package com.example.android.mod9assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class Activity2 : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var itemAdapter: ArrayAdapter<String>
    private lateinit var items: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        listView = findViewById(R.id.listView)
        items = ArrayList()
        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = itemAdapter

        val number = intent.getIntExtra("number", 0)
        generateItemsAsync(number)
    }

    private fun generateItemsAsync(number: Int) {
        val timer = Timer()
        var count = 1

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (count <= number) {
                        val item = "Item $count"
                        items.add(item)
                        itemAdapter.notifyDataSetChanged()
                        count++
                    } else {
                        timer.cancel()
                        timer.purge()
                    }
                }
            }
        }, 0, 1000)

        listView.setOnItemClickListener { _, _, position, _ ->
            val clickedItem = items[position]
            val intent = Intent(this, Activity3::class.java)
            intent.putExtra("clickedItem", clickedItem)
            startActivity(intent)
        }
    }
}
