package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        btnRock.setOnClickListener{ onUserMove(0) }
        btnPaper.setOnClickListener{ onUserMove(1) }
        btnScissors.setOnClickListener{ onUserMove(2) }
    }

    private fun onUserMove(userMove: Int) {
        val winner = winner(userMove, computerMove())
        Toast.makeText(this, "The winner is: $winner", Toast.LENGTH_SHORT).show()
    }

    private fun computerMove(): Int {
        return Random.nextInt(0, 3)
    }

    private fun winner(userMove: Int, computerMove: Int): String{
        if (computerMove == userMove) return "draw"

        return when(userMove) {
            0 -> if (computerMove == 1) {"computer"} else "user"
            1 -> if (computerMove == 2) {"computer"} else "user"
            2 -> if (computerMove == 0) {"computer"} else "user"
            else -> "IMPOSSIBLE!!!"
        }
    }
}
