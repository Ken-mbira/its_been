package com.example.itsbeen.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventList(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {items(5) {item ->
            Event(modifier = modifier)
        }
        }
    }
}

@Composable
fun Event(modifier: Modifier = Modifier) {
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
                text = "Independence in kenya",
                fontSize = 23.sp,
            )
            Text(
                text = "26 Jan 2020",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete Icon",
                modifier = modifier.size(25.dp)
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    EventList()
}