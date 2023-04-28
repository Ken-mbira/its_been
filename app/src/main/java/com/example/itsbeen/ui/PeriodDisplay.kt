package com.example.itsbeen.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itsbeen.NumberWords
import com.example.itsbeen.R
import com.example.itsbeen.ui.components.SpinnerPicker
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun getDuration(startDate: LocalDate, returnType: String): String {
    val now = LocalDate.now()
    val duration = Duration.between(startDate.atStartOfDay(), now.atStartOfDay())

    when (returnType.uppercase()) {
        "SECONDS" -> {
            return duration.seconds.toString()
        }
        "MINUTES" -> {
            return duration.toMinutes().toString()
        }
        "HOURS" -> {
            return duration.toHours().toString()
        }
        "DAYS" -> {
            return duration.toDays().toString()
        }
        "WEEKS" -> {
            return ChronoUnit.WEEKS.between(startDate, now).toString()
        }
        "MONTHS" -> {
            return ChronoUnit.MONTHS.between(startDate, now).toString()
        }
        "YEARS" -> {
            return ChronoUnit.YEARS.between(startDate, now).toString()
        }
        else -> {return "0"}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PeriodDisplay(
    modifier: Modifier = Modifier,
    fromDate: LocalDate?,
) {
    val selectedDuration = rememberSaveable{ mutableStateOf("Seconds") }

    val durationString = if(fromDate != null){
        getDuration(fromDate, selectedDuration.value)
    }else{
        "0"
    }

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
                    text= if(fromDate != null){
                        NumberWords().commaSeparated(durationString)
                    }else{
                         "0"
                         },
                    fontSize = 25.sp
                )
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text= NumberWords().convertToWords(durationString),
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
                    items = mutableListOf("Seconds", "Minutes", "Hours", "Days", "Weeks", "Months", "Years"),
                    emitNewItem = { durationName -> selectedDuration.value = durationName }
                )
            }
        }
    }
}