package com.example.itsbeen.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itsbeen.R

@Composable
fun SinceDisplay(modifier: Modifier = Modifier) {
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
                modifier = modifier,
                border = BorderStroke(0.5.dp, Color.Black),
//                backgroundColor = Color.Transparent
            ) {
                Text(
                    modifier = modifier.padding(10.dp),
                    text = stringResource(R.string.example_date),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = stringResource(R.string.example_description),
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewSince() {
    SinceDisplay()
}