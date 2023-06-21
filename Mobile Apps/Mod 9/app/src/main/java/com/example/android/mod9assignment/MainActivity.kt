package com.example.android.mod9assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var numberEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberEditText = findViewById(R.id.numberEditText)
        val generateButton: Button = findViewById(R.id.generateButton)

        generateButton.setOnClickListener {
            val number = numberEditText.text.toString().toInt()
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("number", number)
            startActivity(intent)
        }
    }
}
