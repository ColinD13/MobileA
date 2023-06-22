package com.example.android.finalproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 5000 // Delay in milliseconds (5 seconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }
}
