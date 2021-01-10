package com.example.fantastats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fantastats.R
import com.example.fantastats.model.*

class SuggestionsFragment : Fragment(R.layout.fragment_suggestions) {

    var basicInformation: BasicInformation? = null
    var bootstrapStatic: BootstrapStatic? = null
    var myPlayers: MyPlayers? = null
    var myTeam: MyTeam? = null
    var injuries: MutableList<Elements> = mutableListOf()
    var suggested: MutableList<Elements> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_suggestions, container, false)
        fillTable(view)
        return view
    }

    private fun fillTable(view: View) {
        chceckInjuryPlayers()

        if (injuries.size == 0) {
            return
        }

        checkSuggestedPlayers()
        fillInjuriesAndSuggested(view)
    }

    private fun checkSuggestedPlayers() {
        injuries.forEach { injury ->
            var suggestion: Elements? = null
            bootstrapStatic?.elements?.forEach { player ->
                if (injury.elementType == player.elementType && player.nowCost.toInt()
                        .isLessThan(
                            (injury.nowCost.toInt() + (myTeam?.transfers?.bank?.toInt() ?: 0))
                        )
                ) {
                    if (((player.elementType == 1 && player.pointsPerGame.isGreaterThan(2.5)) || (player.elementType == 2 && player.pointsPerGame.isGreaterThan(
                            2.0
                        )) || (player.elementType == 3 && player.pointsPerGame.isGreaterThan(2.1)) || (player.elementType == 4 && player.pointsPerGame.isGreaterThan(
                            0.9
                        )) && (player.chanceOfPlayingNextRound == 100 || player.chanceOfPlayingNextRound == null))
                    ) {
                        if (suggestion == null) {
                            suggestion = player
                            return@forEach
                        }

                        if (((player.form.toDouble().isGreaterThan(suggestion?.form?.toDouble())))
                        ) {
                            var pocitadlo = 0
                            myPlayers?.elements?.forEach {
                                if (it.team == player.team) {
                                    pocitadlo++
                                }
                            }
                            if (!pocitadlo.isGreaterThan(3))
                                suggestion = player
                        }
                    }
                }
            }
            suggestion?.let {
                suggested.add(it)
            }
        }
    }

    private fun fillInjuriesAndSuggested(view: View) {
        for (i in 0..6) {

            if ((i + 1).isGreaterThan(injuries.size)) {
                break
            }

            when (i) {
                0 -> {
                    var injuryPlayer1: TextView? = view.findViewById(R.id.injuryPlayer1)
                    injuryPlayer1?.text = this.injuries[i].webName
                    var transferFor01: TextView? = view.findViewById(R.id.transferFor01)
                    transferFor01?.text = this.suggested[i].webName
                    var transferFor1: TextView? = view.findViewById(R.id.transferFor1)
                    transferFor1?.text = this.suggested[i].webName
                    var transferFor1Pos: TextView? = view.findViewById(R.id.transferFor1Pos)
                    transferFor1Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor1Cost: TextView? = view.findViewById(R.id.transferFor1Cost)
                    transferFor1Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor1Form: TextView? = view.findViewById(R.id.transferFor1Form)
                    transferFor1Form?.text = this.suggested[i].form
                    var transferFor1Points: TextView? = view.findViewById(R.id.transferFor1Points)
                    transferFor1Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor1Ppg: TextView? = view.findViewById(R.id.transferFor1Ppg)
                    transferFor1Ppg?.text = this.suggested[i].pointsPerGame.toString()
                }
                1 -> {
                    var injuryPlayer2: TextView? = view.findViewById(R.id.injuryPlayer2)
                    injuryPlayer2?.text = this.injuries[i].webName
                    var transferFor02: TextView? = view.findViewById(R.id.transferFor02)
                    transferFor02?.text = this.suggested[i].webName
                    var transferFor2: TextView? = view.findViewById(R.id.transferFor2)
                    transferFor2?.text = this.suggested[i].webName
                    var transferFor2Pos: TextView? = view.findViewById(R.id.transferFor2Pos)
                    transferFor2Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor2Cost: TextView? = view.findViewById(R.id.transferFor2Cost)
                    transferFor2Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor2Form: TextView? = view.findViewById(R.id.transferFor2Form)
                    transferFor2Form?.text = this.suggested[i].form
                    var transferFor2Points: TextView? = view.findViewById(R.id.transferFor2Points)
                    transferFor2Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor2Ppg: TextView? = view.findViewById(R.id.transferFor2Ppg)
                    transferFor2Ppg?.text = this.suggested[i].pointsPerGame.toString()

                }
                2 -> {
                    var injuryPlayer3: TextView? = view.findViewById(R.id.injuryPlayer3)
                    injuryPlayer3?.text = this.injuries[i].webName
                    var transferFor03: TextView? = view.findViewById(R.id.transferFor03)
                    transferFor03?.text = this.suggested[i].webName
                    var transferFor3: TextView? = view.findViewById(R.id.transferFor3)
                    transferFor3?.text = this.suggested[i].webName
                    var transferFor3Pos: TextView? = view.findViewById(R.id.transferFor3Pos)
                    transferFor3Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor3Cost: TextView? = view.findViewById(R.id.transferFor3Cost)
                    transferFor3Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor3Form: TextView? = view.findViewById(R.id.transferFor3Form)
                    transferFor3Form?.text = this.suggested[i].form
                    var transferFor3Points: TextView? = view.findViewById(R.id.transferFor3Points)
                    transferFor3Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor3Ppg: TextView? = view.findViewById(R.id.transferFor3Ppg)
                    transferFor3Ppg?.text = this.suggested[i].pointsPerGame.toString()
                }
                3 -> {
                    var injuryPlayer4: TextView? = view.findViewById(R.id.injuryPlayer4)
                    injuryPlayer4?.text = this.injuries[i].webName
                    var transferFor04: TextView? = view.findViewById(R.id.transferFor04)
                    transferFor04?.text = this.suggested[i].webName
                    var transferFor4: TextView? = view.findViewById(R.id.transferFor4)
                    transferFor4?.text = this.suggested[i].webName
                    var transferFor4Pos: TextView? = view.findViewById(R.id.transferFor4Pos)
                    transferFor4Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor4Cost: TextView? = view.findViewById(R.id.transferFor4Cost)
                    transferFor4Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor4Form: TextView? = view.findViewById(R.id.transferFor4Form)
                    transferFor4Form?.text = this.suggested[i].form
                    var transferFor4Points: TextView? = view.findViewById(R.id.transferFor4Points)
                    transferFor4Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor4Ppg: TextView? = view.findViewById(R.id.transferFor4Ppg)
                    transferFor4Ppg?.text = this.suggested[i].pointsPerGame.toString()
                }
                4 -> {
                    var injuryPlayer5: TextView? = view.findViewById(R.id.injuryPlayer5)
                    injuryPlayer5?.text = this.injuries[i].webName
                    var transferFor05: TextView? = view.findViewById(R.id.transferFor05)
                    transferFor05?.text = this.suggested[i].webName
                    var transferFor5: TextView? = view.findViewById(R.id.transferFor5)
                    transferFor5?.text = this.suggested[i].webName
                    var transferFor5Pos: TextView? = view.findViewById(R.id.transferFor5Pos)
                    transferFor5Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor5Cost: TextView? = view.findViewById(R.id.transferFor5Cost)
                    transferFor5Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor5Form: TextView? = view.findViewById(R.id.transferFor5Form)
                    transferFor5Form?.text = this.suggested[i].form
                    var transferFor5Points: TextView? = view.findViewById(R.id.transferFor5Points)
                    transferFor5Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor5Ppg: TextView? = view.findViewById(R.id.transferFor5Ppg)
                    transferFor5Ppg?.text = this.suggested[i].pointsPerGame.toString()
                }
                5 -> {
                    var injuryPlayer6: TextView? = view.findViewById(R.id.injuryPlayer6)
                    injuryPlayer6?.text = this.injuries[i].webName
                    var transferFor06: TextView? = view.findViewById(R.id.transferFor06)
                    transferFor06?.text = this.suggested[i].webName
                    var transferFor6: TextView? = view.findViewById(R.id.transferFor6)
                    transferFor6?.text = this.suggested[i].webName
                    var transferFor6Pos: TextView? = view.findViewById(R.id.transferFor6Pos)
                    transferFor6Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor6Cost: TextView? = view.findViewById(R.id.transferFor6Cost)
                    transferFor6Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor6Form: TextView? = view.findViewById(R.id.transferFor6Form)
                    transferFor6Form?.text = this.suggested[i].form
                    var transferFor6Points: TextView? = view.findViewById(R.id.transferFor6Points)
                    transferFor6Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor6Ppg: TextView? = view.findViewById(R.id.transferFor6Ppg)
                    transferFor6Ppg?.text = this.suggested[i].pointsPerGame.toString()
                }
                6 -> {
                    var injuryPlayer7: TextView? = view.findViewById(R.id.injuryPlayer7)
                    injuryPlayer7?.text = this.injuries[i].webName
                    var transferFor07: TextView? = view.findViewById(R.id.transferFor07)
                    transferFor07?.text = this.suggested[i].webName
                    var transferFor7: TextView? = view.findViewById(R.id.transferFor7)
                    transferFor7?.text = this.suggested[i].webName
                    var transferFor7Pos: TextView? = view.findViewById(R.id.transferFor7Pos)
                    transferFor7Pos?.text = when (this.suggested[i].elementType) {
                        1 -> {
                            "GKP"
                        }
                        2 -> {
                            "DEF"
                        }
                        3 -> {
                            "MID"
                        }
                        4 -> {
                            "FWD"
                        }
                        else -> ""
                    }
                    var transferFor7Cost: TextView? = view.findViewById(R.id.transferFor7Cost)
                    transferFor7Cost?.text = this.suggested[i].nowCost?.let { changeCost(it) }
                    var transferFor7Form: TextView? = view.findViewById(R.id.transferFor7Form)
                    transferFor7Form?.text = this.suggested[i].form
                    var transferFor7Points: TextView? = view.findViewById(R.id.transferFor7Points)
                    transferFor7Points?.text = this.suggested[i].totalPoints.toString()
                    var transferFor7Ppg: TextView? = view.findViewById(R.id.transferFor7Ppg)
                    transferFor7Ppg?.text = this.suggested[i].pointsPerGame.toString()
                }
            }
        }
    }

    private fun chceckInjuryPlayers() {
        myPlayers?.elements?.forEach { player ->
            if (player.chanceOfPlayingNextRound == 0 || player.chanceOfPlayingNextRound == 25 || player.chanceOfPlayingNextRound == 50 || player.chanceOfPlayingNextRound == 75) {
                myTeam?.picks?.forEach {
                    if (player.id == it.element) {
                        player.nowCost = it.sellingPrice.toString()
                        injuries.add(player)
                    }
                }
            }
        }
        println(injuries)
    }

    fun Int?.isGreaterThan(other: Int?) =
        this != null && other != null && this > other

    fun Double?.isGreaterThan(other: Double?) =
        this != null && other != null && this > other

    fun Int?.isLessThan(other: Int?) =
        this != null && other != null && this < other


    private fun changeCost(str: String): String {
        val dot = "."
        var value = str.substring(0, str.length - 1)
        value = value + dot + str.last() + "M"
        return value
    }

}