package com.example.rockpaperscissors.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpaperscissors.model.Game


@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDB : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameDBInstance: GameDB? = null

        fun getDatabase(context: Context): GameDB? {
            if (gameDBInstance == null) {
                synchronized(GameDB::class.java) {
                    if (gameDBInstance == null) {
                        gameDBInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameDB::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return gameDBInstance
        }
    }
}