package com.example.rockpaperscissors

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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

    private var games = arrayListOf<Game>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable up button

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

    private fun getGamesFromDB() {  // Updates rv with all db entries
        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@HistoryActivity.games.clear()
            this@HistoryActivity.games.addAll(games)
            viewAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuDeleteHistory -> deleteHistory()
        }

        return when (item.itemId) {
            R.id.menuDeleteHistory -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllProducts()
            }
            getGamesFromDB()
        }
    }

    override fun onSupportNavigateUp(): Boolean {  // Makes back button work! TODO: Fix back button clearing database.
        finish()
        return true
    }
}