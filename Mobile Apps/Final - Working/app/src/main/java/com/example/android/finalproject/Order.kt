package com.example.android.finalproject

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Order : AppCompatActivity() {


    private lateinit var selectedFoodOne: TextView
    private lateinit var selectedFoodTwo : TextView
    private lateinit var  drink : TextView
    private lateinit var drinkChoice : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_layout)

        selectedFoodOne = findViewById(R.id.selectedFoodOne)
        selectedFoodTwo = findViewById(R.id.selectedFoodTwo)
        drink = findViewById(R.id.drink)
        drinkChoice = findViewById(R.id.radioGroup)

        selectedFoodOne.text = "Pizza"
        selectedFoodTwo.text = "Burger"
        drink.text = "Add drink"


    }
}