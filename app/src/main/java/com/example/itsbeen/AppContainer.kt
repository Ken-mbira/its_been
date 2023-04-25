package com.example.itsbeen

import android.content.Context
import com.example.itsbeen.data.EventDatabase
import com.example.itsbeen.data.EventRepository

class AppContainer(private val context: Context) {
    val eventRepository: EventRepository = EventRepository(EventDatabase.getDatabase(context).eventDao())
}