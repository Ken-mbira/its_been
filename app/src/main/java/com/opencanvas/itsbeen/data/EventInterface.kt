package com.opencanvas.itsbeen.data

import com.opencanvas.itsbeen.model.Event
import kotlinx.coroutines.flow.Flow

interface EventInterface {
    suspend fun addEvent(event: Event)

    suspend fun getEvent(eventId: Int): Flow<Event>

    suspend fun listEvents(searchParam: String?): Flow<List<Event>>

    suspend fun deleteEvent(event: Event)

}