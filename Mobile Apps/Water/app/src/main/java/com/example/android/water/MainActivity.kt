package com.example.android.water

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.TextView
import androidx.core.view.marginTop

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Layout components
        var outline = findViewById<TextView>(R.id.outline)
        var button = findViewById(R.id.drink_water) as Button

        //Running code every X seconds
        var handler : Handler = Handler()
        val delay : Long= 1000
        var waterLevel = 2500

        //Lowers water every X amount of ms
        outline.text = waterLevel.toString() + "ml"


        handler.postDelayed(object:Runnable{
            override fun run()
            {
                if(waterLevel>0)
                    waterLevel--;

                handler.postDelayed(this, delay)
                outline.text = waterLevel.toString() + "ml"
            }
        }, delay)

        button.setOnClickListener(View.OnClickListener {
            waterLevel = 2500
            outline.text = waterLevel.toString() + "ml"
        })

    }
}