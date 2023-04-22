package com.example.itsbeen.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itsbeen.R
import com.example.itsbeen.ui.components.SpinnerPicker

@Composable
fun PeriodDisplay(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Card(
            modifier = modifier
                .weight(5f)
                .height(100.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(
                modifier = modifier.padding(10.dp)
            ) {
                Text(
                    text="12,121,121",
                    fontSize = 25.sp
                )
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text="Twelve million, one hundred and twenty one thousand, one hundred and twenty one",
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = modifier.width(5.dp))
        Card(
            modifier = modifier
                .weight(2f)
                .height(100.dp)
        ) {
            Column(
                modifier = modifier
                    .background(color = colorResource(R.color.faded_purple)),
            ) {
                SpinnerPicker(
                    items = mutableListOf("Seconds", "Minutes", "Hours", "Days", "Week", "Month", "Year"),
                )
            }
        }
    }
}