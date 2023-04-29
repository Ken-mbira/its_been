package com.opencanvas.itsbeen.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opencanvas.itsbeen.model.Event
import com.opencanvas.itsbeen.R

@Composable
fun SinceDisplay(
    modifier: Modifier = Modifier,
    event: Event
) {
    Row(modifier = modifier) {
        Box(modifier = modifier) {
            Text(
                text = stringResource(R.string.since_text),
                fontSize = 40.sp
            )
        }
        Spacer(
            modifier = modifier.width(20.dp)
        )
        Column(
            modifier = modifier
        ) {
            Spacer(
                modifier = modifier.height(10.dp)
            )
            Card(
                modifier = modifier
                    .width(150.dp),
                border = BorderStroke(0.5.dp, Color.Black),
            ) {
                Text(
                    modifier = modifier.padding(10.dp),
                    text = event.date,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = event.name,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewSince() {
    SinceDisplay(
        event = Event(name="Day of Days", date = "2022/12/12")
    )
}