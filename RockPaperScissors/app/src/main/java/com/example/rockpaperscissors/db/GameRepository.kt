package com.example.rockpaperscissors.db

import android.content.Context
import com.example.rockpaperscissors.model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameDB = GameDB.getDatabase(context)
        gameDao = gameDB!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun deleteAllProducts() = gameDao.deleteAllProducts()

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }
}
