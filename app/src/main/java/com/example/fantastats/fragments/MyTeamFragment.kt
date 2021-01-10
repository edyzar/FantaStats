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

class MyTeamFragment : Fragment(R.layout.fragment_my_team) {

    var basicInformation: BasicInformation? = null
    var myPlayers: MyPlayers? = null
    var myTeam: MyTeam? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_my_team, container, false)
        fillTable(view)
        return view
    }

    private fun fillTable(view: View) {
        var teamTextView: TextView? = view.findViewById(R.id.teamTextView)
        teamTextView?.text = this.basicInformation?.name
        fillGoalies(view)
        fillDefenders(view)
        fillMidfielders(view)
        fillForwards(view)
    }

    private fun fillGoalies(view: View) {
        var fgName: TextView? = view.findViewById(R.id.fgName)
        fgName?.text = this.myPlayers?.elements?.get(0)?.webName

        var fgSel: TextView? = view.findViewById(R.id.fgSelection)
        fgSel?.text = this.myPlayers?.elements?.get(0)?.selectedByPercent + "%"

        var fgForm: TextView? = view.findViewById(R.id.fgForm)
        fgForm?.text = this.myPlayers?.elements?.get(0)?.form

        var fgPoints: TextView? = view.findViewById(R.id.fgPoints)
        fgPoints?.text = this.myPlayers?.elements?.get(0)?.totalPoints.toString()

        var fgChop: TextView? = view.findViewById(R.id.fgChop)
        fgChop?.text =
            (if (this.myPlayers?.elements?.get(0)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(0)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fgCost: TextView? = view.findViewById(R.id.fgCost)
        fgCost?.text = this.myPlayers?.elements?.get(0)?.nowCost?.let { changeCost(it) }

        var sgName: TextView? = view.findViewById(R.id.sgName)
        sgName?.text = this.myPlayers?.elements?.get(1)?.webName

        var sgSel: TextView? = view.findViewById(R.id.sgSel)
        sgSel?.text = this.myPlayers?.elements?.get(1)?.selectedByPercent + "%"

        var sgForm: TextView? = view.findViewById(R.id.sgForm)
        sgForm?.text = this.myPlayers?.elements?.get(1)?.form

        var sgPoints: TextView? = view.findViewById(R.id.sgPoints)
        sgPoints?.text = this.myPlayers?.elements?.get(1)?.totalPoints.toString()

        var sgChop: TextView? = view.findViewById(R.id.sgChop)
        sgChop?.text =
            (if (this.myPlayers?.elements?.get(1)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(1)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var sgCost: TextView? = view.findViewById(R.id.sgCost)
        sgCost?.text = this.myPlayers?.elements?.get(1)?.nowCost?.let { changeCost(it) }
    }

    private fun fillDefenders(view: View) {
        var fdName: TextView? = view.findViewById(R.id.fdName)
        fdName?.text = this.myPlayers?.elements?.get(2)?.webName

        var fdSel: TextView? = view.findViewById(R.id.fdSel)
        fdSel?.text = this.myPlayers?.elements?.get(2)?.selectedByPercent + "%"

        var fdForm: TextView? = view.findViewById(R.id.fdForm)
        fdForm?.text = this.myPlayers?.elements?.get(2)?.form

        var fdPoints: TextView? = view.findViewById(R.id.fdPoints)
        fdPoints?.text = this.myPlayers?.elements?.get(2)?.totalPoints.toString()

        var fdChop: TextView? = view.findViewById(R.id.fdChop)
        fdChop?.text =
            (if (this.myPlayers?.elements?.get(2)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(2)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fdCost: TextView? = view.findViewById(R.id.fdCost)
        fdCost?.text = this.myPlayers?.elements?.get(2)?.nowCost?.let { changeCost(it) }

        var sdName: TextView? = view.findViewById(R.id.sdName)
        sdName?.text = this.myPlayers?.elements?.get(3)?.webName

        var sdSel: TextView? = view.findViewById(R.id.sdSel)
        sdSel?.text = this.myPlayers?.elements?.get(3)?.selectedByPercent + "%"

        var sdForm: TextView? = view.findViewById(R.id.sdForm)
        sdForm?.text = this.myPlayers?.elements?.get(3)?.form

        var sdPoints: TextView? = view.findViewById(R.id.sdPoints)
        sdPoints?.text = this.myPlayers?.elements?.get(3)?.totalPoints.toString()

        var sdChop: TextView? = view.findViewById(R.id.sdChop)
        sdChop?.text =
            (if (this.myPlayers?.elements?.get(3)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(3)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var sdCost: TextView? = view.findViewById(R.id.sdCost)
        sdCost?.text = this.myPlayers?.elements?.get(3)?.nowCost?.let { changeCost(it) }

        var tdName: TextView? = view.findViewById(R.id.tdName)
        tdName?.text = this.myPlayers?.elements?.get(4)?.webName

        var tdSel: TextView? = view.findViewById(R.id.tdSel)
        tdSel?.text = this.myPlayers?.elements?.get(4)?.selectedByPercent + "%"

        var tdForm: TextView? = view.findViewById(R.id.tdForm)
        tdForm?.text = this.myPlayers?.elements?.get(4)?.form

        var tdPoints: TextView? = view.findViewById(R.id.tdPoints)
        tdPoints?.text = this.myPlayers?.elements?.get(4)?.totalPoints.toString()

        var tdChop: TextView? = view.findViewById(R.id.tdChop)
        tdChop?.text =
            (if (this.myPlayers?.elements?.get(4)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(4)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var tdCost: TextView? = view.findViewById(R.id.tdCost)
        tdCost?.text = this.myPlayers?.elements?.get(4)?.nowCost?.let { changeCost(it) }

        var fodName: TextView? = view.findViewById(R.id.fodName)
        fodName?.text = this.myPlayers?.elements?.get(5)?.webName

        var fodSel: TextView? = view.findViewById(R.id.fodSel)
        fodSel?.text = this.myPlayers?.elements?.get(5)?.selectedByPercent + "%"

        var fodForm: TextView? = view.findViewById(R.id.fodForm)
        fodForm?.text = this.myPlayers?.elements?.get(5)?.form

        var fodPoints: TextView? = view.findViewById(R.id.fodPoints)
        fodPoints?.text = this.myPlayers?.elements?.get(5)?.totalPoints.toString()

        var fodChop: TextView? = view.findViewById(R.id.fodChop)
        fodChop?.text =
            (if (this.myPlayers?.elements?.get(5)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(5)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fodCost: TextView? = view.findViewById(R.id.fodCost)
        fodCost?.text = this.myPlayers?.elements?.get(5)?.nowCost?.let { changeCost(it) }

        var fidName: TextView? = view.findViewById(R.id.fidName)
        fidName?.text = this.myPlayers?.elements?.get(6)?.webName

        var fidSel: TextView? = view.findViewById(R.id.fidSel)
        fidSel?.text = this.myPlayers?.elements?.get(6)?.selectedByPercent + "%"

        var fidForm: TextView? = view.findViewById(R.id.fidForm)
        fidForm?.text = this.myPlayers?.elements?.get(6)?.form

        var fidPoints: TextView? = view.findViewById(R.id.fidPoints)
        fidPoints?.text = this.myPlayers?.elements?.get(6)?.totalPoints.toString()

        var fidChop: TextView? = view.findViewById(R.id.fidChop)
        fidChop?.text =
            (if (this.myPlayers?.elements?.get(6)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(6)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fidCost: TextView? = view.findViewById(R.id.fidCost)
        fidCost?.text = this.myPlayers?.elements?.get(6)?.nowCost?.let { changeCost(it) }
    }

    private fun fillMidfielders(view: View) {
        var fmName: TextView? = view.findViewById(R.id.fmName)
        fmName?.text = this.myPlayers?.elements?.get(7)?.webName

        var fmSel: TextView? = view.findViewById(R.id.fmSel)
        fmSel?.text = this.myPlayers?.elements?.get(7)?.selectedByPercent + "%"

        var fmForm: TextView? = view.findViewById(R.id.fmForm)
        fmForm?.text = this.myPlayers?.elements?.get(7)?.form

        var fmPoints: TextView? = view.findViewById(R.id.fmPoints)
        fmPoints?.text = this.myPlayers?.elements?.get(7)?.totalPoints.toString()

        var fmChop: TextView? = view.findViewById(R.id.fmChop)
        fmChop?.text =
            (if (this.myPlayers?.elements?.get(7)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(7)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fmCost: TextView? = view.findViewById(R.id.fmCost)
        fmCost?.text = this.myPlayers?.elements?.get(7)?.nowCost?.let { changeCost(it) }

        var smName: TextView? = view.findViewById(R.id.smName)
        smName?.text = this.myPlayers?.elements?.get(8)?.webName

        var smSel: TextView? = view.findViewById(R.id.smSel)
        smSel?.text = this.myPlayers?.elements?.get(8)?.selectedByPercent + "%"

        var smForm: TextView? = view.findViewById(R.id.smForm)
        smForm?.text = this.myPlayers?.elements?.get(8)?.form

        var smPoints: TextView? = view.findViewById(R.id.smPoints)
        smPoints?.text = this.myPlayers?.elements?.get(8)?.totalPoints.toString()

        var smChop: TextView? = view.findViewById(R.id.smChop)
        smChop?.text =
            (if (this.myPlayers?.elements?.get(8)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(8)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var smCost: TextView? = view.findViewById(R.id.smCost)
        smCost?.text = this.myPlayers?.elements?.get(8)?.nowCost?.let { changeCost(it) }

        var tmName: TextView? = view.findViewById(R.id.tmName)
        tmName?.text = this.myPlayers?.elements?.get(9)?.webName

        var tmSel: TextView? = view.findViewById(R.id.tmSel)
        tmSel?.text = this.myPlayers?.elements?.get(9)?.selectedByPercent + "%"

        var tmForm: TextView? = view.findViewById(R.id.tmForm)
        tmForm?.text = this.myPlayers?.elements?.get(9)?.form

        var tmPoints: TextView? = view.findViewById(R.id.tmPoints)
        tmPoints?.text = this.myPlayers?.elements?.get(9)?.totalPoints.toString()

        var tmChop: TextView? = view.findViewById(R.id.tmChop)
        tmChop?.text =
            (if (this.myPlayers?.elements?.get(9)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(9)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var tmCost: TextView? = view.findViewById(R.id.tmCost)
        tmCost?.text = this.myPlayers?.elements?.get(9)?.nowCost?.let { changeCost(it) }

        var fomName: TextView? = view.findViewById(R.id.fomName)
        fomName?.text = this.myPlayers?.elements?.get(10)?.webName

        var fomSel: TextView? = view.findViewById(R.id.fomSel)
        fomSel?.text = this.myPlayers?.elements?.get(10)?.selectedByPercent + "%"

        var fomForm: TextView? = view.findViewById(R.id.fomForm)
        fomForm?.text = this.myPlayers?.elements?.get(10)?.form

        var fomPoints: TextView? = view.findViewById(R.id.fomPoints)
        fomPoints?.text = this.myPlayers?.elements?.get(10)?.totalPoints.toString()

        var fomChop: TextView? = view.findViewById(R.id.fomChop)
        fomChop?.text =
            (if (this.myPlayers?.elements?.get(10)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(10)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fomCost: TextView? = view.findViewById(R.id.fomCost)
        fomCost?.text = this.myPlayers?.elements?.get(10)?.nowCost?.let { changeCost(it) }

        var fimName: TextView? = view.findViewById(R.id.fimName)
        fimName?.text = this.myPlayers?.elements?.get(11)?.webName

        var fimSel: TextView? = view.findViewById(R.id.fimSel)
        fimSel?.text = this.myPlayers?.elements?.get(11)?.selectedByPercent + "%"

        var fimForm: TextView? = view.findViewById(R.id.fimForm)
        fimForm?.text = this.myPlayers?.elements?.get(11)?.form

        var fimPoints: TextView? = view.findViewById(R.id.fimPoints)
        fimPoints?.text = this.myPlayers?.elements?.get(11)?.totalPoints.toString()

        var fimChop: TextView? = view.findViewById(R.id.fimChop)
        fimChop?.text =
            (if (this.myPlayers?.elements?.get(11)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(11)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var fimCost: TextView? = view.findViewById(R.id.fimCost)
        fimCost?.text = this.myPlayers?.elements?.get(11)?.nowCost?.let { changeCost(it) }
    }

    private fun fillForwards(view: View) {
        var ffName: TextView? = view.findViewById(R.id.ffName)
        ffName?.text = this.myPlayers?.elements?.get(12)?.webName

        var ffSel: TextView? = view.findViewById(R.id.ffSel)
        ffSel?.text = this.myPlayers?.elements?.get(12)?.selectedByPercent + "%"

        var ffForm: TextView? = view.findViewById(R.id.ffForm)
        ffForm?.text = this.myPlayers?.elements?.get(12)?.form

        var ffPoints: TextView? = view.findViewById(R.id.ffPoints)
        ffPoints?.text = this.myPlayers?.elements?.get(12)?.totalPoints.toString()

        var ffChop: TextView? = view.findViewById(R.id.ffChop)
        ffChop?.text =
            (if (this.myPlayers?.elements?.get(12)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(12)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var ffCost: TextView? = view.findViewById(R.id.ffCost)
        ffCost?.text = this.myPlayers?.elements?.get(12)?.nowCost?.let { changeCost(it) }

        var sfName: TextView? = view.findViewById(R.id.sfName)
        sfName?.text = this.myPlayers?.elements?.get(13)?.webName

        var sfSel: TextView? = view.findViewById(R.id.sfSel)
        sfSel?.text = this.myPlayers?.elements?.get(13)?.selectedByPercent + "%"

        var sfForm: TextView? = view.findViewById(R.id.sfForm)
        sfForm?.text = this.myPlayers?.elements?.get(13)?.form

        var sfPoints: TextView? = view.findViewById(R.id.sfPoints)
        sfPoints?.text = this.myPlayers?.elements?.get(13)?.totalPoints.toString()

        var sfChop: TextView? = view.findViewById(R.id.sfChop)
        sfChop?.text =
            (if (this.myPlayers?.elements?.get(13)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(13)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var sfCost: TextView? = view.findViewById(R.id.sfCost)
        sfCost?.text = this.myPlayers?.elements?.get(13)?.nowCost?.let { changeCost(it) }

        var tfName: TextView? = view.findViewById(R.id.tfName)
        tfName?.text = this.myPlayers?.elements?.get(14)?.webName

        var tfSel: TextView? = view.findViewById(R.id.tfSel)
        tfSel?.text = this.myPlayers?.elements?.get(14)?.selectedByPercent + "%"

        var tfForm: TextView? = view.findViewById(R.id.tfForm)
        tfForm?.text = this.myPlayers?.elements?.get(14)?.form

        var tfPoints: TextView? = view.findViewById(R.id.tfPoints)
        tfPoints?.text = this.myPlayers?.elements?.get(14)?.totalPoints.toString()

        var tfChop: TextView? = view.findViewById(R.id.tfChop)
        tfChop?.text =
            (if (this.myPlayers?.elements?.get(14)?.chanceOfPlayingNextRound == null) {
                "100"
            } else {
                this.myPlayers?.elements?.get(14)?.chanceOfPlayingNextRound.toString()
            } + "%")

        var tfCost: TextView? = view.findViewById(R.id.tfCost)
        tfCost?.text = this.myPlayers?.elements?.get(14)?.nowCost?.let { changeCost(it) }

    }

    private fun changeCost(str: String): String {
        val dot = "."
        var value = str.substring(0, str.length - 1)
        value = value + dot + str.last() + "M"
        return value
    }

}