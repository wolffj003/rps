package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.db.GameRepository
import com.example.rockpaperscissors.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var gameRepository: GameRepository

    var games = arrayListOf<Game>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewManager = LinearLayoutManager(this)
        viewAdapter = GameAdapter(games)

        recyclerView = findViewById<RecyclerView>(R.id.rvGames).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        gameRepository = GameRepository(this)

        initViews()
    }

    private fun initViews() {
        getGamesFromDB()
    }

    private fun getGamesFromDB() {  // For filling AND updating rv
        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@HistoryActivity.games.clear()
            this@HistoryActivity.games.addAll(games)
            viewAdapter.notifyDataSetChanged()
        }
    }
}