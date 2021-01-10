package com.example.fantastats.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.fantastats.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        val roundAlertTime =
            arrayOf("1 hour before", "2 hour before", "4 hour before", "1 day before")
        val spinner: Spinner = view.findViewById(R.id.roundAlert)
        spinner.adapter =
            activity?.let {
                ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    roundAlertTime
                )
            } as SpinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Error Settings Fragment")
            }

        }

        val switch: Switch = view.findViewById(R.id.injuryAlert)
        switch?.setOnCheckedChangeListener { _, isChecked ->
            val msg = if (isChecked) "on" else "off"
            switch.text = msg
        }


        return view
    }

    private fun fillNotifications(view: View, container: ViewGroup?) {


        //var roundAlert: Spinner? = view.findViewById(R.id.roundAlert)
        //roundAlert?. = this.myPlayers?.elements?.get(0)?.webName
    }
}