package com.example.android.finalproject

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SubMenuActivity : AppCompatActivity() {

    private lateinit var submenuTitleTextView: TextView
    private lateinit var foodGridView: GridView
    private lateinit var selectedFoodTextView: TextView
    private lateinit var selectedFoodImageView: ImageView
    private lateinit var backButton: Button
    private lateinit var addToOrderButton: Button

    private var selectedFood: FoodItem? = null
    private val orderList: MutableList<FoodItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submenu)

        submenuTitleTextView = findViewById(R.id.submenu_title)
        foodGridView = findViewById(R.id.food_grid)
        backButton = findViewById(R.id.back_button)
        addToOrderButton = findViewById(R.id.add_to_order_button)

        val submenuTitle = intent.getStringExtra("submenu_title")
        submenuTitleTextView.text = submenuTitle

        // Dummy data for food items
        val foodItems = listOf(
            FoodItem("Food 1", R.drawable.food1),
            FoodItem("Food 2", R.drawable.food2),
            FoodItem("Food 3", R.drawable.food3),
            FoodItem("Food 4", R.drawable.food4)
        )

        val foodAdapter = FoodAdapter(this, foodItems)
        foodGridView.adapter = foodAdapter



        backButton.setOnClickListener {
            finish()
        }

        addToOrderButton.setOnClickListener {
            if (selectedFood != null) {
                orderList.add(selectedFood!!)
                Toast.makeText(this, "Added to order", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No food item selected", Toast.LENGTH_SHORT).show()
            }
        }

        foodGridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedFoodItem = foodItems[position]
                selectedFoodTextView.text = "Selected Food: ${selectedFoodItem.name}"
                selectedFood = selectedFoodItem
            }
    }

    // Rest of the code...
}
