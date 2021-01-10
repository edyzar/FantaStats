package com.example.fantastats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fantastats.R
import com.example.fantastats.model.BasicInformation
import com.example.fantastats.model.MyPlayers
import com.example.fantastats.model.MyTeam

class HomeFragment : Fragment(R.layout.fragment_home) {

    var basicInformation: BasicInformation? = null
    var myTeam: MyTeam? = null
    var wildcard: String = ""
    var triple: String = ""
    var boost: String = ""
    var freeHit: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)
        fillTable(view)
        return view
    }

    private fun fillTable(view: View) {

        fillPoints(view)
        fillTransferAndFinance(view)
        fillChips(view)
        fillLeague(view)

    }

    private fun fillLeague(view: View) {

        for (i in 0..7) {
            if ((i + 1).isGreaterThan(this.basicInformation?.leagues?.classic?.size)) {
                break
            }

            when (i) {
                0 -> {
                    var leagueName1: TextView? = view.findViewById(R.id.leagueName1)
                    leagueName1?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank1: TextView? = view.findViewById(R.id.leagueRank1)
                    leagueRank1?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                1 -> {
                    var leagueName2: TextView? = view.findViewById(R.id.leagueName2)
                    leagueName2?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank2: TextView? = view.findViewById(R.id.leagueRank2)
                    leagueRank2?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                2 -> {
                    var leagueName3: TextView? = view.findViewById(R.id.leagueName3)
                    leagueName3?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank3: TextView? = view.findViewById(R.id.leagueRank3)
                    leagueRank3?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                3 -> {
                    var leagueName4: TextView? = view.findViewById(R.id.leagueName4)
                    leagueName4?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank4: TextView? = view.findViewById(R.id.leagueRank4)
                    leagueRank4?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                4 -> {
                    var leagueName5: TextView? = view.findViewById(R.id.leagueName5)
                    leagueName5?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank5: TextView? = view.findViewById(R.id.leagueRank5)
                    leagueRank5?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                5 -> {
                    var leagueName6: TextView? = view.findViewById(R.id.leagueName6)
                    leagueName6?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank6: TextView? = view.findViewById(R.id.leagueRank6)
                    leagueRank6?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                6 -> {
                    var leagueName7: TextView? = view.findViewById(R.id.leagueName7)
                    leagueName7?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank7: TextView? = view.findViewById(R.id.leagueRank7)
                    leagueRank7?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
                7 -> {
                    var leagueName8: TextView? = view.findViewById(R.id.leagueName8)
                    leagueName8?.text = this.basicInformation?.leagues?.classic?.get(i)?.name
                    var leagueRank8: TextView? = view.findViewById(R.id.leagueRank8)
                    leagueRank8?.text =
                        this.basicInformation?.leagues?.classic?.get(i)?.entryRank.toString()
                }
            }
        }
    }

    private fun fillChips(view: View) {

        myTeam?.chips?.forEach { muj ->
            if (muj.name.equals("wildcard")) {
                wildcard = muj.statusForEntry
            } else if (muj.name.equals("freehit")) {
                freeHit = muj.statusForEntry
            } else if (muj.name.equals("bboost")) {
                boost = muj.statusForEntry
            } else if (muj.name.equals("3xc")) {
                triple = muj.statusForEntry
            }
        }

        var wildcardd: TextView? = view.findViewById(R.id.wildcard)
        wildcardd?.text = this.wildcard

        var triple: TextView? = view.findViewById(R.id.tripleCaptain)
        triple?.text = this.triple

        var boost: TextView? = view.findViewById(R.id.benchBoost)
        boost?.text = this.boost

        var freeHit: TextView? = view.findViewById(R.id.freeHit)
        freeHit?.text = this.freeHit
    }

    private fun fillTransferAndFinance(view: View) {
        var squadValue: TextView? = view.findViewById(R.id.squadValue)
        squadValue?.text = this.myTeam?.transfers?.value?.let { changeCost(it) }

        var inTheBank: TextView? = view.findViewById(R.id.inTheBank)
        inTheBank?.text = this.myTeam?.transfers?.bank?.let { changeCost(it) }

        var gameweekTransfer: TextView? = view.findViewById(R.id.gameweekTransfer)
        gameweekTransfer?.text = this.myTeam?.transfers?.made.toString()

        var freeTranfer: TextView? = view.findViewById(R.id.freeTransfers)
        freeTranfer?.text = (this.myTeam?.transfers?.made?.let {
            this.myTeam?.transfers?.limit?.minus(
                it
            )
        }).toString()
    }

    private fun fillPoints(view: View) {
        var manager: TextView? = view.findViewById(R.id.manager)
        manager?.text =
            this.basicInformation?.playerFirstName + " " + this.basicInformation?.playerLastName

        var summaryEventPoints: TextView? = view.findViewById(R.id.summaryEventPoints)
        summaryEventPoints?.text = this.basicInformation?.summaryEventPoints.toString()

        var summaryOverallPoints: TextView? = view.findViewById(R.id.summaryOverallPoints)
        summaryOverallPoints?.text = this.basicInformation?.summaryOverallPoints.toString()

        var summaryRank: TextView? = view.findViewById(R.id.summaryOverallRank)
        summaryRank?.text = this.basicInformation?.summaryOverallRank.toString()
    }

    private fun changeCost(str: String): String {
        val dot = "."
        var value = str.substring(0, str.length - 1)
        value = if (str.length == 1) {
            "0" + value + dot + str.last() + "M"
        } else {
            value + dot + str.last() + "M"
        }

        return value
    }

    fun Int?.isGreaterThan(other: Int?) =
        this != null && other != null && this > other

}