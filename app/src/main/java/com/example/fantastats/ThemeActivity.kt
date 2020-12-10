package com.example.fantastats

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 2500)

    }
}