package com.example.rockpaperscissors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: ArrayList<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolderCard>() {

    class ViewHolderCard(cardViewText: View) : RecyclerView.ViewHolder(cardViewText) {
        fun bind(game: Game) {
            itemView.cvtvResult.text = game.winnerText
            itemView.cvtvDate.text = game.dateText

            when(game.computerMove) {
                0 -> itemView.cvivComputerMove.setImageResource(R.drawable.rock)
                1 -> itemView.cvivComputerMove.setImageResource(R.drawable.paper)
                2 -> itemView.cvivComputerMove.setImageResource(R.drawable.scissors)
            }

            when(game.userMove) {
                0 -> itemView.cvivUserMove.setImageResource(R.drawable.rock)
                1 -> itemView.cvivUserMove.setImageResource(R.drawable.paper)
                2 -> itemView.cvivUserMove.setImageResource(R.drawable.scissors)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderCard {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return ViewHolderCard(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolderCard, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}
