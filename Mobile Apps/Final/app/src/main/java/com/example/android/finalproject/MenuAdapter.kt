package com.example.android.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MenuAdapter(private val context: Context, private val menuItems: List<MenuItem>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_menu, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val menuItem = menuItems[position]

        viewHolder.menuIcon.setImageResource(menuItem.iconResId)
        viewHolder.menuTitle.text = menuItem.title

        return view
    }

    override fun getItem(position: Int): Any {
        return menuItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return menuItems.size
    }

    private class ViewHolder(view: View) {
        val menuIcon: ImageView = view.findViewById(R.id.menu_icon)
        val menuTitle: TextView = view.findViewById(R.id.menu_title)
    }

}

