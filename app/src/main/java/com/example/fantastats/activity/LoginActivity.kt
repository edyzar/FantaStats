package com.example.fantastats.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fantastats.R
import com.example.fantastats.createStatsService
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
                intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("Id", me.player.entry)
                intent.putExtra("plProfile", plProfile.text.toString())
                intent.putExtra("sessionId", sessionId.text.toString())
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
                    val me =
                        //service.me(cookie = "pl_profile=eyJzIjogIld6SXNOVGcwTXpZM01EVmQ6MWt5ZUJoOndBR1VsRXhSeEE3RUpuUXBFMlUtZ3Rqa21xUSIsICJ1IjogeyJpZCI6IDU4NDM2NzA1LCAiZm4iOiAic2Rmc2Rmc2YiLCAibG4iOiAiYXNkZnNkZnNkZiIsICJmYyI6IDE0fX0=; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIytTAxNjM3MFXSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKDqVFNDUwvL1KREs0SLJKNUQxMD81QTQxOzVMu0FMu0NEvDVLMUQ6MkC6VaAIbKLLU:1kyeBi:nhXdevRL1S5hRTBQUj5WeVbTG3Q")
                        service.me(cookie = "pl_profile=" + plProfile.text + ";sessionid=" + sessionId.text)
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
