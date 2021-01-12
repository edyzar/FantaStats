package com.example.fantastats.activity

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.work.*
import com.example.fantastats.R
import com.example.fantastats.UploadWorker
import com.example.fantastats.createStatsService
import com.example.fantastats.fragments.HomeFragment
import com.example.fantastats.fragments.MyTeamFragment
import com.example.fantastats.fragments.SettingsFragment
import com.example.fantastats.fragments.SuggestionsFragment
import com.example.fantastats.model.*
import com.example.fantastats.notification.Reminder
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class MenuActivity : AppCompatActivity() {

    private var myTeam: MyTeam? = null
    private var myPlayers: MyPlayers = MyPlayers(elements = null)
    private var bootstrapStatic: BootstrapStatic? = null
    private var basicInformation: BasicInformation? = null
    private var isInjured = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        createNotificationChannel()
        runNotification()

        myTeam = loadMujTym()

        myPlayers.elements =
            findMyTeam(bootstrapStatic?.elements!!, myTeam)?.sortedBy { it.elementType }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val homeFragment = HomeFragment()

        homeFragment.myTeam = myTeam
        homeFragment.basicInformation = basicInformation

        val settingsFragment = SettingsFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> sendToHomeFragment(homeFragment)
                R.id.myTeamFragment -> sendToMyTeamFragment(myTeam, myPlayers)
                R.id.suggestionsFragment -> sendToSuggestionFragment(myTeam, myPlayers)
                R.id.settingsFragment -> setCurrentFragment(settingsFragment)
            }
            true
        }

        checkInjuryPlayers()
        scheduleNotification()

    }

    private fun scheduleNotification() {
        val data = Data.Builder()

        data.putString("isInjured", (if (isInjured) {"true"} else {"false"}))

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadWorkRequest = PeriodicWorkRequest.Builder(
            UploadWorker::class.java,
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraint)
            .addTag("my_unique_worker")
            .setInputData(data.build())
            .build()
        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork("my_unique_worker", ExistingPeriodicWorkPolicy.REPLACE,
                uploadWorkRequest)
        
    }

    private fun runNotification() {
        if (isInjured) {
            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show()

            var intentt = Intent(this, Reminder::class.java)
            var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intentt, 0)

            var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

            val time = System.currentTimeMillis()

            val tenSecond = 1000 * 10

            alarmManager.set(AlarmManager.RTC_WAKEUP, time + tenSecond, pendingIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT.isGreaterThan(Build.VERSION_CODES.O)) {
            val name = "NotificationTest"
            val description = "Channel for Notification Test"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("notificationOne", name, importance)
            channel.description = description

            var notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun findMyTeam(elements: List<Elements>, myTeam: MyTeam?): List<Elements>? {
        var myPlayers: MutableList<Elements>? = mutableListOf()

        myTeam?.picks?.forEach { muj ->
            elements.forEach {
                if (muj.element == it.id) {
                    myPlayers?.add(it)
                }
            }
        }
        return myPlayers
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()

        }

    private fun sendToMyTeamFragment(myTeam: MyTeam?, myPlayers: MyPlayers?) {
        val myTeamFragment = MyTeamFragment()
        myTeamFragment.myTeam = myTeam
        myTeamFragment.myPlayers = myPlayers
        myTeamFragment.basicInformation = basicInformation
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, myTeamFragment).commit()
    }

    private fun sendToSuggestionFragment(myTeam: MyTeam?, myPlayers: MyPlayers?) {
        val suggestionsFragment = SuggestionsFragment()
        suggestionsFragment.myTeam = myTeam
        suggestionsFragment.myPlayers = myPlayers
        suggestionsFragment.bootstrapStatic = bootstrapStatic
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, suggestionsFragment).commit()
    }

    private fun sendToHomeFragment(homeFragment: HomeFragment) {
        homeFragment.myTeam = myTeam
        homeFragment.basicInformation = basicInformation
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, homeFragment).commit()
    }

    fun Int?.isGreaterThan(other: Int?) =
        this != null && other != null && this > other

    private fun checkInjuryPlayers() {
        myPlayers?.elements?.forEach { player ->
            if (player.chanceOfPlayingNextRound == 0 || player.chanceOfPlayingNextRound == 25 || player.chanceOfPlayingNextRound == 50 || player.chanceOfPlayingNextRound == 75) {
                myTeam?.picks?.forEach {
                    if (player.id == it.element) {
                        isInjured = true
                    }
                }
            }
        }
    }

    private fun loadMujTym(): MyTeam? {
        var myTeam: MyTeam? = null
        runBlocking {
            val job = GlobalScope.launch {
                val service = createStatsService()
                val id = intent.getIntExtra("Id", 0)
                val plProfile = intent.getStringExtra("plProfile")
                val sessionId = intent.getStringExtra("sessionId")
                if (id == 0) {
                    Log.e("MenuActivity", "Neplatné id týmu")
                } else {
                    try {
                        /* myTeam = service.myTeam(
                             cookie = "pl_profile=eyJzIjogIld6SXNNamt5TmpNMk9EWmQ6MWtyZnpqOmNnbzV1M2hUVmxsQjF3dEN4VjNYZlFQd211QSIsICJ1IjogeyJpZCI6IDI5MjYzNjg2LCAiZm4iOiAiRWR3YXJkIiwgImxuIjogIlphcmVja3kiLCAiZmMiOiAxNH19; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIysjQyMzazMFPSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKBqS0MTy8S0VHNjI7OUlFTzFENjw1QzY1MLQ0uzZAPDVEMDCxOL1DRDS6VaAHxnK_U:1krfzj:jBhVfus_ZCwioNU_6t3dx5okYTU)",
                             id = id
                         )*/
                        /*myTeam = service.myTeam(
                            cookie = "pl_profile=eyJzIjogIld6SXNOVGcwTXpZM01EVmQ6MWt5ZUJoOndBR1VsRXhSeEE3RUpuUXBFMlUtZ3Rqa21xUSIsICJ1IjogeyJpZCI6IDU4NDM2NzA1LCAiZm4iOiAic2Rmc2Rmc2YiLCAibG4iOiAiYXNkZnNkZnNkZiIsICJmYyI6IDE0fX0=; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIytTAxNjM3MFXSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKDqVFNDUwvL1KREs0SLJKNUQxMD81QTQxOzVMu0FMu0NEvDVLMUQ6MkC6VaAIbKLLU:1kyeBi:nhXdevRL1S5hRTBQUj5WeVbTG3Q",
                            id = id
                        )*/
                        myTeam = service.myTeam(
                            cookie = "pl_profile=" + plProfile + ";sessionid=" + sessionId, id = id
                        )
                        basicInformation = service.basicInformation(id = id)
                        bootstrapStatic = service.bootstrapStatic()
                    } catch (th: Throwable) {
                        Log.e("MenuActivity", th.message, th)
                    }
                }
            }
            job.join()
        }
        return myTeam
    }

}