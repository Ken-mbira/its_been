package com.example.itsbeen.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.itsbeen.EventApplication
import com.example.itsbeen.data.EventRepository
import com.example.itsbeen.model.Event
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar

class EventViewModel(private val eventRepository: EventRepository): ViewModel() {

    private val _eventListStateFlow = MutableStateFlow(listOf<Event>())
    val eventListState = _eventListStateFlow.asStateFlow()

    private val _stagedEventStateFlow = MutableStateFlow(Event(name = "", date = ""))
    val stagedEventState = _stagedEventStateFlow.asStateFlow()

    fun createEvent(event: Event) {
        viewModelScope.launch {
            eventRepository.addEvent(event)
        }
    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            eventRepository.deleteEvent(event)
        }
    }

    fun stageEvent(event: Event) {
        _stagedEventStateFlow.update { event }
    }

    init {
        viewModelScope.launch {
            eventRepository.listEvents(null).collect {events ->
                _eventListStateFlow.update { events }
                stageEvent(events.first())
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EventApplication)
                val eventRepository = application.container.eventRepository
                EventViewModel(eventRepository = eventRepository)
            }
        }
    }
}