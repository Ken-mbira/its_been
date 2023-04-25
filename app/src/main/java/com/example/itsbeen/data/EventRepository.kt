package com.example.itsbeen.data

import com.example.itsbeen.model.Event
import com.example.itsbeen.model.EventDao

class EventRepository(private val eventDao: EventDao): EventInterface {
    override suspend fun addEvent(event: Event) = eventDao.insertEvent(event)

    override suspend fun getEvent(eventId: Int) = eventDao.getEvent(eventId)

    override suspend fun listEvents(searchParam: String?) = eventDao.getAllEvents()

    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)

}