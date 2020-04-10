package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        val computerMove = computerMove()
        val winner = winner(userMove, computerMove)
        Toast.makeText(this, "The winner is: $winner", Toast.LENGTH_SHORT).show()

        updateView(userMove, computerMove)

        // TODO: Save game to DB
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

    private fun updateView(userMove: Int, computerMove: Int) {
        when(userMove) {
            0 -> ivUserMove.setImageResource(R.drawable.rock)
            1 -> ivUserMove.setImageResource(R.drawable.paper)
            2 -> ivUserMove.setImageResource(R.drawable.scissors)
        }

        when(computerMove) {
            0 -> ivComputerMove.setImageResource(R.drawable.rock)
            1 -> ivComputerMove.setImageResource(R.drawable.paper)
            2 -> ivComputerMove.setImageResource(R.drawable.scissors)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startHistoryActivity()

        return when (item.itemId) {
            R.id.action_history -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startHistoryActivity() {  // Super uitgebreid in andere opdrachten, maar hier alleen om te switchen naar history. Er hoeft niets worden opgeslagen ofzo.
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}
