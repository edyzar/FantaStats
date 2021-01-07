package com.example.fantastats

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fantastats.fragments.HomeFragment
import com.example.fantastats.fragments.MyTeamFragment
import com.example.fantastats.fragments.SettingsFragment
import com.example.fantastats.fragments.SuggestionsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        nactiMujTym()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val homeFragment = HomeFragment()
        val myTeamFragment = MyTeamFragment()
        val suggestionsFragment = SuggestionsFragment()
        val settingsFragment = SettingsFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> setCurrentFragment(homeFragment)
                R.id.myTeamFragment -> setCurrentFragment(myTeamFragment)
                R.id.suggestionsFragment -> setCurrentFragment(suggestionsFragment)
                R.id.settingsFragment -> setCurrentFragment(settingsFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()

    }

    private fun nactiMujTym() {
        lifecycleScope.launch {
            val service = createStatsService()
            val id = intent.getIntExtra("Id", 0)
            if (id == 0) {
                Log.e("MenuActivity", "Neplatné id týmu")
            } else {
                try {
                    val result = service.bootstrapStatic()
                } catch (th: Throwable) {
                    Log.e("MenuActivity", th.message, th)
                }
            }
        }
    }

}