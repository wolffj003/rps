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

    @ColumnInfo(name = "winner")
    var winner: String,

    @ColumnInfo(name = "result")
    val resultText: String,
    @ColumnInfo(name = "date")
    val dateText: String,

    @ColumnInfo(name = "userMove")
    val userMove: Int,
    @ColumnInfo(name = "computerMove")
    val computerMove: Int
    ) : Parcelable
