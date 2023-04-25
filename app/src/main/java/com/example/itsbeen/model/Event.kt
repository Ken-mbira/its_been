package com.example.itsbeen.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "event")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val date: String
)