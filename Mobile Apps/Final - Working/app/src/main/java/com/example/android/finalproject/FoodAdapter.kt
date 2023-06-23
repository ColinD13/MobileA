package com.example.android.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class FoodAdapter(private val context: Context, private val foodItems: List<FoodItem>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item_food, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val foodItem = foodItems[position]

        viewHolder.foodName.text = foodItem.name
        viewHolder.foodImage.setImageResource(foodItem.imageResId)

        return view
    }

    override fun getItem(position: Int): Any {
        return foodItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return foodItems.size
    }

    private class ViewHolder(view: View) {
        val foodName: TextView = view.findViewById(R.id.food_name)
        val foodImage: ImageView = view.findViewById(R.id.food_image)
    }
}
