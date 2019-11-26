package com.example.tercerparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var playerList: MutableList<Player>
    lateinit var ref:DatabaseReference
    lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("players")

        listView=findViewById(R.id.listView)

        btnSave.setOnClickListener {
            savePlayer()
        }

        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    for(p in p0.children){
                        val player = p.getValue(Player::class.java)
                        playerList.add(player!!)
                    }

                    val adapter = PlayerAdapter(applicationContext,R.layout.players,playerList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }

        })

    }

    private fun savePlayer(){
        val name = editName.text.toString().trim()
        val lastName = editLastName.text.toString().trim()
        val team = editTeam.text.toString().trim()
        val position = editPosition.text.toString().trim()
        val number = editNumber.text.toString().trim()


        if(name.isEmpty()){
            editName.error = "Please enter your name"
            return
        }


        val playerId = ref.push().key

        val player = playerId?.let { Player(it,name,lastName,team,position,number,playerRating.numStars) }

        if (playerId != null) {
            ref.child(playerId).setValue(player).addOnCompleteListener {
                Toast.makeText(applicationContext,"Player saved succesfully!",Toast.LENGTH_LONG).show()
            }
        }

    }
}
