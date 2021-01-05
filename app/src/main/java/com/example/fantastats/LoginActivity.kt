package com.example.fantastats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            if (prihlaseni()) {
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                println("Neplatné přihlašovací údaje")
            }
        }
    }

    private fun prihlaseni(): Boolean {
        var existuje = false
        runBlocking {
            val job = GlobalScope.launch {
                val service = createStatsService()
                try {
                    //val me = service.me(cookie = "pl_profile=eyJzIjogIld6SXNNamt5TmpNMk9EWmQ6MWtyZnpqOmNnbzV1M2hUVmxsQjF3dEN4VjNYZlFQd211QSIsICJ1IjogeyJpZCI6IDI5MjYzNjg2LCAiZm4iOiAiRWR3YXJkIiwgImxuIjogIlphcmVja3kiLCAiZmMiOiAxNH19; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIysjQyMzazMFPSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKBqS0MTy8S0VHNjI7OUlFTzFENjw1QzY1MLQ0uzZAPDVEMDCxOL1DRDS6VaAHxnK_U:1krfzj:jBhVfus_ZCwioNU_6t3dx5okYTU)")
                    val me = service.me(cookie = "")
                    if (me.player != null) {
                        existuje = true
                    }
                } catch (th: Throwable) {
                    existuje = false
                    Log.e("LoginActivity", th.message, th)
                }
            }
            job.join()
        }
        return existuje
    }

}
