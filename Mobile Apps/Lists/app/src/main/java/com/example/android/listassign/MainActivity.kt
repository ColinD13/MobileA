package com.example.android.listassign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val sweet = ArrayList<String>()
    private val savory = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //sweet
        val list1 = findViewById<RecyclerView>(R.id.sweetList)
        val sweetButton = findViewById<Button>(R.id.sweetButton)
        val text = findViewById<TextInputEditText>(R.id.sweetEdit)
        sweetButton.setOnClickListener{
            sweet.add(text.text.toString())
//            text.setText("");
            findViewById<TextView>(R.id.sweetEdit).setText("")
            list1.adapter = CustomAdapter(sweet)
            list1.layoutManager = LinearLayoutManager(applicationContext)
        }
        //savory
        val list2 = findViewById<RecyclerView>(R.id.savoryList)
        val savoryButton = findViewById<Button>(R.id.savoryButton)
        val text2 = findViewById<TextInputEditText>(R.id.savoryEdit)
        savoryButton.setOnClickListener{
            savory.add(text2.text.toString())
//            text.setText("");
            findViewById<TextView>(R.id.savoryEdit).setText("")
            list2.adapter = CustomAdapter(savory)
            list2.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}

internal class CustomAdapter(private var itemsList: List<String>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}