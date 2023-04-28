package com.example.itsbeen.data

import com.example.itsbeen.model.Event
import com.example.itsbeen.model.EventDao
import kotlinx.coroutines.flow.Flow

class EventRepository(private val eventDao: EventDao): EventInterface {
    override suspend fun addEvent(event: Event) = eventDao.insertEvent(event)

    override suspend fun getEvent(eventId: Int) = eventDao.getEvent(eventId)

    override suspend fun listEvents(searchParam: String?): Flow<List<Event>> {
        return if (searchParam != null){
            eventDao.searchEventsByName(searchParam)
        }else{
            eventDao.getAllEvents()
        }
    }

    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)

}