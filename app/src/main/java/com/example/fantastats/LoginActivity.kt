package com.example.fantastats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fetchJson()

        loginButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    private fun fetchJson() {
        lifecycleScope.launch {
            val service = createPremierLeagueService()
            try {
                val result = service.bootstrapStatic()
                println(result.totalPlayers)
            } catch (th: Throwable) {
                Log.e("LoginActivity", th.message, th)
            }
        }
    }

}
