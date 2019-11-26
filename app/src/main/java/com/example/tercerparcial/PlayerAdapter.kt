package com.example.tercerparcial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*

class PlayerAdapter(val mCtx: Context,val layoutResId:Int,val  playerList: List<Player>)
    :ArrayAdapter<Player>(mCtx,layoutResId,playerList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewLastName = view.findViewById<TextView>(R.id.textViewLastName)
        val textViewTeam= view.findViewById<TextView>(R.id.textViewTeam)
        val textViewPosition= view.findViewById<TextView>(R.id.textViewPosition)
        val textViewNumber= view.findViewById<TextView>(R.id.textViewNumber)

        val player = playerList[position]

        textViewName.text = player.name
        textViewLastName.text = player.lastName
        textViewTeam.text = player.team
        textViewPosition.text = player.position
        textViewNumber.text = player.number

        return view

    }
}