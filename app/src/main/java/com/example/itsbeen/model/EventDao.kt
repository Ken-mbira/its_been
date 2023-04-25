package com.example.itsbeen.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event)

    @Query("SELECT * FROM event ORDER BY id ASC")
    fun getAllEvents(): Flow<List<Event>>

    @Delete
    suspend fun delete(event:Event)

    @Query("SELECT * FROM event WHERE name LIKE '%' || :pattern || '%'")
    fun searchEventsByName(pattern: String): Flow<List<Event>>

    @Query("SELECT * FROM event WHERE id = :id")
    fun getEvent(id: Int): Flow<Event>
}