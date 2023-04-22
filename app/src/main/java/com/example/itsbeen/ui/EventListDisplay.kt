package com.example.itsbeen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itsbeen.ui.components.EventList
import com.example.itsbeen.ui.components.SearchBar

@Composable
fun EventListDisplay(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(top = 30.dp)) {
        SearchBar()
        EventList()
    }
}

@Preview
@Composable
fun PreviewMemories() {
    EventListDisplay()
}