package com.example.fantastats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fantastats.model.Me
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            val me: Me? = prihlaseni()
            if (me != null) {
                intent  = Intent(this, MenuActivity::class.java)
                intent.putExtra("Id", me.player.id)
                startActivity(intent)
            } else {
                println("Neplatné přihlašovací údaje")
            }
        }
    }

    private fun prihlaseni(): Me? {
        var user: Me? = null
        runBlocking {
            val job = GlobalScope.launch {
                val service = createStatsService()
                try {
                    val me = service.me(cookie = "pl_profile=eyJzIjogIld6SXNNamt5TmpNMk9EWmQ6MWtyZnpqOmNnbzV1M2hUVmxsQjF3dEN4VjNYZlFQd211QSIsICJ1IjogeyJpZCI6IDI5MjYzNjg2LCAiZm4iOiAiRWR3YXJkIiwgImxuIjogIlphcmVja3kiLCAiZmMiOiAxNH19; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIysjQyMzazMFPSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKBqS0MTy8S0VHNjI7OUlFTzFENjw1QzY1MLQ0uzZAPDVEMDCxOL1DRDS6VaAHxnK_U:1krfzj:jBhVfus_ZCwioNU_6t3dx5okYTU)")
                    if (me.player != null) {
                        user = me
                    }
                } catch (th: Throwable) {
                    Log.e("LoginActivity", th.message, th)
                }
            }
            job.join()
        }
        return user
    }

}
