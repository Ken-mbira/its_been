package com.example.itsbeen.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itsbeen.R
import com.example.itsbeen.model.Event

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    eventList: List<Event>,
    deleteEvent: (Event) -> Unit = {}
) {
    Card(
        modifier = modifier,
        backgroundColor =
            if(isSystemInDarkTheme()){
                Color.DarkGray
            }else{
                Color.White
            },
        elevation = 10.dp
    ) {
        LazyColumn(
            modifier = modifier
                .height(300.dp)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {items(eventList.count()) {event ->
            Event(
                modifier = modifier,
                event = eventList[event],
                deleteEvent = deleteEvent
            )
        }
        }
    }
}

@Composable
fun Event(
    modifier: Modifier = Modifier,
    event: Event,
    deleteEvent: (Event) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier.weight(3f)
        ) {
            Text(
                text = event.name,
                fontSize = 17.sp,
            )
            Text(
                text = event.date,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )
        }
        IconButton(
            onClick = { deleteEvent(event) },
            modifier = modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(R.string.delete_icon),
                modifier = modifier.size(25.dp)
            )
        }
    }
}