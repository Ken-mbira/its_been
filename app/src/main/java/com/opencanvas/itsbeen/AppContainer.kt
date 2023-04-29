package com.opencanvas.itsbeen

import android.content.Context
import com.opencanvas.itsbeen.data.EventDatabase
import com.opencanvas.itsbeen.data.EventRepository

class AppContainer(private val context: Context) {
    val eventRepository: EventRepository = EventRepository(EventDatabase.getDatabase(context).eventDao())
}