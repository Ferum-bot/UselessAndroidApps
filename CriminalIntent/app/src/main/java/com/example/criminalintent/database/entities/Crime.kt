package com.example.criminalintent.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "crime_list_table")
data class Crime(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "date")
    val date: Date = Date(),

    @ColumnInfo(name = "is_disclosed")
    val isDisclosed: Boolean = false
)