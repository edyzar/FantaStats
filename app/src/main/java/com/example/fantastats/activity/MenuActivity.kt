package com.example.fantastats.activity

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.example.fantastats.notification.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MenuActivity : AppCompatActivity() {

    private var myTeam: MyTeam? = null
    private var myPlayers: MyPlayers = MyPlayers(elements = null)
    private var bootstrapStatic: BootstrapStatic? = null
    private var basicInformation: BasicInformation? = null
    private var history: History? = null
    private var isInjured = false
    private var milisecondsThirtyMinutes: Long? = null
    private var milisecondsOneHour: Long? = null
    private var milisecondsTwoHours: Long? = null
    private var milisecondsTenMinutes: Long? = null
    private var milisecondsDay: Long? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        createNotificationChannels()

        myTeam = loadMujTym()

        findNextDeadline()
        sendNotifications()

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

    private fun sendNotifications() {
        sendTenMinutesNotification()
        sendThirtyMinutesNotification()
        sendOneHourNotification()
        sendTwoHoursNotification()
        sendDayNotification()
    }

    private fun findNextDeadline() {
        var deadlines: MutableList<Deadline>? = mutableListOf()

        bootstrapStatic?.events?.forEach { event ->
            val array: List<String> = event.deadline_time.split("T")

            val arrayDays: List<String> = array[0].split("-")

            val arrayHours: List<String> = array[1].split("Z")

            val arrayTime: List<String> = arrayHours[0].split(":")

            var deadline: Deadline? = Deadline(
                year = arrayDays[0], month = arrayDays[1], days = arrayDays[2],
                hours = arrayTime[0], minutes = arrayTime[1], seconds = arrayTime[2]
            )

            if (deadline != null) {
                deadlines?.add(deadline)

            }
        }

        var lastGameweek = history?.current?.last()?.event?.plus(1)
        var nextDeadline = Deadline("", "", "", "", "", "")
        bootstrapStatic?.events?.forEach { events ->
            if (events.id == lastGameweek) {
                val array: List<String> = events.deadline_time.split("T")

                val arrayDays: List<String> = array[0].split("-")

                val arrayHours: List<String> = array[1].split("Z")

                val arrayTime: List<String> = arrayHours[0].split(":")

                nextDeadline = Deadline(
                    year = arrayDays[0],
                    month = arrayDays[1],
                    days = arrayDays[2],
                    hours = arrayTime[0].toInt().plus(1).toString(),
                    minutes = arrayTime[1],
                    seconds = arrayTime[2]
                )
            }
        }

        milisecondsTenMinutes = toMiliseconds(
            nextDeadline.year + "-" + nextDeadline.month + "-" + nextDeadline.days + "-" + nextDeadline.hours + ":" + nextDeadline.minutes + ":" + nextDeadline.seconds,
            "ten"
        )
        milisecondsThirtyMinutes = toMiliseconds(
            nextDeadline.year + "-" + nextDeadline.month + "-" + nextDeadline.days + "-" + nextDeadline.hours + ":" + nextDeadline.minutes + ":" + nextDeadline.seconds,
            "thirty"
        )
        milisecondsOneHour = toMiliseconds(
            nextDeadline.year + "-" + nextDeadline.month + "-" + nextDeadline.days + "-" + nextDeadline.hours + ":" + nextDeadline.minutes + ":" + nextDeadline.seconds,
            "one"
        )
        milisecondsTwoHours = toMiliseconds(
            nextDeadline.year + "-" + nextDeadline.month + "-" + nextDeadline.days + "-" + nextDeadline.hours + ":" + nextDeadline.minutes + ":" + nextDeadline.seconds,
            "two"
        )
        milisecondsDay = toMiliseconds(
            nextDeadline.year + "-" + nextDeadline.month + "-" + nextDeadline.days + "-" + nextDeadline.hours + ":" + nextDeadline.minutes + ":" + nextDeadline.seconds,
            "day"
        )

    }

    private fun toMiliseconds(date: String, type: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        val mDate: Date = sdf?.parse(date);
        var timeInMilliseconds = 0L;
        when (type) {
            "ten" -> {
                timeInMilliseconds = mDate.time.minus(600 * 1000)
            }
            "thirty" -> {
                timeInMilliseconds = mDate.time.minus(1800 * 1000)
            }
            "one" -> {
                timeInMilliseconds = mDate.time.minus(3600 * 1000)
            }
            "two" -> {
                timeInMilliseconds = mDate.time.minus(7200 * 1000)
            }
            "day" -> {
                timeInMilliseconds = mDate.time.minus(86400 * 1000)
            }
        }

        return timeInMilliseconds

    }

    private fun scheduleNotification() {
        val data = Data.Builder()

        data.putString(
            "isInjured", (if (isInjured) {
                "true"
            } else {
                "false"
            })
        )

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadWorkRequest = PeriodicWorkRequest.Builder(
            UploadWorker::class.java,
            1, TimeUnit.HOURS
        )
            .setConstraints(constraint)
            .addTag("my_unique_worker")
            .setInputData(data.build())
            .build()
        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork(
                "my_unique_worker", ExistingPeriodicWorkPolicy.REPLACE,
                uploadWorkRequest
            )

    }

    private fun sendTenMinutesNotification() {
        var intentt = Intent(this, TenMinutes::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intentt, 0)
        var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        milisecondsTenMinutes?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                it,
                pendingIntent
            )
        }
    }

    private fun sendThirtyMinutesNotification() {
        var intentt = Intent(this, ThirtyMinutes::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intentt, 0)
        var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        milisecondsThirtyMinutes?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                it,
                pendingIntent
            )
        }
    }

    private fun sendOneHourNotification() {
        var intentt = Intent(this, OneHour::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intentt, 0)
        var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        milisecondsOneHour?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                it,
                pendingIntent
            )
        }
    }

    private fun sendTwoHoursNotification() {
        var intentt = Intent(this, TwoHours::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intentt, 0)
        var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        milisecondsTwoHours?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                it,
                pendingIntent
            )
        }
    }

    private fun sendDayNotification() {
        var intentt = Intent(this, OneDay::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intentt, 0)
        var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        milisecondsTwoHours?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                it,
                pendingIntent
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT.isGreaterThan(Build.VERSION_CODES.O)) {
            val name = "tenMinutes"
            val description = "Channel for Notification Test ten minutes"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("tenMinutes", name, importance)
            channel.description = description

            var notificationManagerFourHours: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManagerFourHours.createNotificationChannel(channel)

            val nameThirtyMinutes = "thirtyMinutes"
            val descriptionThirtyMinutes = "Channel for Notification Test thirty minutes"
            val importanceThirtyMinutes = NotificationManager.IMPORTANCE_DEFAULT
            val channelThirtyMinutes =
                NotificationChannel("thirtyMinutes", nameThirtyMinutes, importanceThirtyMinutes)
            channelThirtyMinutes.description = descriptionThirtyMinutes

            var notificationManagerThirtyMinutes: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManagerThirtyMinutes.createNotificationChannel(channelThirtyMinutes)

            val nameOneHour = "oneHours"
            val descriptionOneHour = "Channel for Notification Test one hour"
            val importanceOneHour = NotificationManager.IMPORTANCE_DEFAULT
            val channelOneHour = NotificationChannel("oneHours", nameOneHour, importanceOneHour)
            channelOneHour.description = descriptionOneHour

            var notificationManagerOneHour: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManagerOneHour.createNotificationChannel(channelOneHour)

            val nameTwoHours = "twoHours"
            val descriptionTwoHours = "Channel for Notification Test Two hours"
            val importanceTwoHours = NotificationManager.IMPORTANCE_DEFAULT
            val channelTwoHours = NotificationChannel("twoHours", nameTwoHours, importanceTwoHours)
            channelTwoHours.description = descriptionTwoHours

            var notificationManagerTwoHours: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManagerTwoHours.createNotificationChannel(channelTwoHours)

            val nameDay = "oneDay"
            val descriptionDay = "Channel for Notification Test one day"
            val importanceDay = NotificationManager.IMPORTANCE_DEFAULT
            val channelDay = NotificationChannel("oneDay", nameDay, importanceDay)
            channel.description = descriptionDay

            var notificationManagerFourHoursDay: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManagerFourHoursDay.createNotificationChannel(channelDay)
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
                        myTeam = service.myTeam(
                            cookie = "pl_profile=$plProfile;sessionid=$sessionId", id = id
                        )
                        basicInformation = service.basicInformation(id = id)
                        bootstrapStatic = service.bootstrapStatic()
                        history = service.history(id = id)
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