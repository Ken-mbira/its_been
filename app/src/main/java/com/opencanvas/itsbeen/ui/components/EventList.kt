package com.opencanvas.itsbeen.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opencanvas.itsbeen.R
import com.opencanvas.itsbeen.model.Event

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    eventList: List<Event>,
    currentQuery: String = "",
    deleteEvent: (Event) -> Unit = {},
    stageEvent: (Event) -> Unit = {}
) {
    Card(
        modifier = modifier.height(300.dp),
        backgroundColor =
            if(isSystemInDarkTheme()){
                Color.DarkGray
            }else{
                Color.White
            },
        elevation = 10.dp
    ) {
        if(eventList.isEmpty() && currentQuery.isBlank()){
            EmptyList()
        }else{
            LazyColumn(
                modifier = modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {items(eventList.count()) {event ->
                Event(
                    modifier = modifier,
                    event = eventList[event],
                    deleteEvent = deleteEvent,
                    stageEvent = stageEvent
                )
            }
        }
        }
    }
}

@Composable
fun Event(
    modifier: Modifier = Modifier,
    event: Event,
    deleteEvent: (Event) -> Unit = {},
    stageEvent: (Event) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .weight(3f)
                .clickable { stageEvent(event) }
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