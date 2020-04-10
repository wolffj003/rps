package com.example.rockpaperscissors.db

import androidx.room.*
import com.example.rockpaperscissors.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames(): List<Game>

    @Query("DELETE FROM gameTable") // Delete * FROM gameTable? WTF de * mist?!
    suspend fun deleteAllProducts()

    @Insert
    suspend fun insertGame(reminder: Game)

    @Delete
    suspend fun deleteGame(reminder: Game)

    @Update
    suspend fun updateGame(reminder: Game)
}
