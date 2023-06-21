package com.example.android.mod9assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity3 : AppCompatActivity() {
    private lateinit var clickedItemTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)

        clickedItemTextView = findViewById(R.id.clickedItemTextView)
        val clickedItem = intent.getStringExtra("clickedItem")
        clickedItemTextView.text = "You clicked $clickedItem"
    }
}
