package com.example.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
data class Game(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,
    @ColumnInfo(name = "winner")  // Loser veld overbodig. > Als geen winner = draw. Als winner = andere loser.
    val winnerText: String,
    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "userMove")
    val userMove: String,
    @ColumnInfo(name = "computerMove")
    val computerMove: String
    ) : Parcelable
