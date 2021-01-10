package com.example.fantastats

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.fantastats.fragments.HomeFragment
import com.example.fantastats.fragments.MyTeamFragment
import com.example.fantastats.fragments.SettingsFragment
import com.example.fantastats.fragments.SuggestionsFragment
import com.example.fantastats.model.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MenuActivity : AppCompatActivity() {

    private var myTeam: MyTeam? = null
    private var myPlayers: MyPlayers = MyPlayers(elements = null)
    private var bootstrapStatic: BootstrapStatic? = null
    private var basicInformation: BasicInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

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

    private fun loadMujTym(): MyTeam? {
        var myTeam: MyTeam? = null
        runBlocking {
            val job = GlobalScope.launch {
                val service = createStatsService()
                val id = intent.getIntExtra("Id", 0)
                if (id == 0) {
                    Log.e("MenuActivity", "Neplatné id týmu")
                } else {
                    try {
                        /* myTeam = service.myTeam(
                             cookie = "pl_profile=eyJzIjogIld6SXNNamt5TmpNMk9EWmQ6MWtyZnpqOmNnbzV1M2hUVmxsQjF3dEN4VjNYZlFQd211QSIsICJ1IjogeyJpZCI6IDI5MjYzNjg2LCAiZm4iOiAiRWR3YXJkIiwgImxuIjogIlphcmVja3kiLCAiZmMiOiAxNH19; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIysjQyMzazMFPSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKBqS0MTy8S0VHNjI7OUlFTzFENjw1QzY1MLQ0uzZAPDVEMDCxOL1DRDS6VaAHxnK_U:1krfzj:jBhVfus_ZCwioNU_6t3dx5okYTU)",
                             id = id
                         )*/
                        myTeam = service.myTeam(
                            cookie = "pl_profile=eyJzIjogIld6SXNOVGcwTXpZM01EVmQ6MWt5ZUJoOndBR1VsRXhSeEE3RUpuUXBFMlUtZ3Rqa21xUSIsICJ1IjogeyJpZCI6IDU4NDM2NzA1LCAiZm4iOiAic2Rmc2Rmc2YiLCAibG4iOiAiYXNkZnNkZnNkZiIsICJmYyI6IDE0fX0=; sessionid=.eJyrVopPLC3JiC8tTi2Kz0xRslIytTAxNjM3MFXSQZZKSkzOTs0DyRfkpBXk6IFk9AJ8QoFyxcHB_o5ALqqGjMTiDKDqVFNDUwvL1KREs0SLJKNUQxMD81QTQxOzVMu0FMu0NEvDVLMUQ6MkC6VaAIbKLLU:1kyeBi:nhXdevRL1S5hRTBQUj5WeVbTG3Q",
                            id = id
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