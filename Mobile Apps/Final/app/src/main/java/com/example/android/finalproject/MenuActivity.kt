package com.example.android.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var menuListView: ListView
    private lateinit var menuAdapter: MenuAdapter

    private val menuItems = listOf(
        MenuItem("Breakfast", R.drawable.ic_breakfast),
        MenuItem("Lunch", R.drawable.ic_lunch),
        MenuItem("Dinner", R.drawable.ic_dinner),
        MenuItem("Desserts", R.drawable.ic_desserts),
        MenuItem("My Order", R.drawable.ic_order)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        menuListView = findViewById(R.id.menu_list)
        menuAdapter = MenuAdapter(this@MenuActivity, menuItems)
        menuListView.adapter = menuAdapter

        menuListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedItem = menuItems[position]

                // Handle item click based on position or item title
                when (position) {
                    0 -> openSubMenu("Breakfast")
                    1 -> openSubMenu("Lunch")
                    2 -> openSubMenu("Dinner")
                    3 -> openSubMenu("Desserts")
                    4 -> openMyOrder()
                }
            }
    }

    private fun openSubMenu(submenuTitle: String) {
        val intent = Intent(this, SubMenuActivity::class.java)
        intent.putExtra("submenu_title", submenuTitle)
        startActivity(intent)
    }

    private fun openMyOrder() {
        // Add your logic for My Order screen
        Toast.makeText(this, "My Order clicked", Toast.LENGTH_SHORT).show()
    }
}
