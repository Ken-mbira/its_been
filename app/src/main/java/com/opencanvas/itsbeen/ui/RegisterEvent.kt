package com.opencanvas.itsbeen.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opencanvas.itsbeen.model.Event
import java.util.*

@Composable
fun RegisterEvent(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onSubmitRequest: (event: Event) -> Unit = {},
) {
    val calendar = Calendar.getInstance()

    val eventSummary = rememberSaveable {
        mutableStateOf("")
    }
    val eventDate = rememberSaveable {
        mutableStateOf("")
    }

    val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        eventDate.value = "$year/${month + 1}/$dayOfMonth"
    }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        dateSetListener,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
    )

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                if(isSystemInDarkTheme()){
                    Color.Black
                }else{
                    Color.White
                }
            )
            .padding(15.dp)
    ) {
        Text(
            text = "Register Event",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = eventSummary.value,
            onValueChange = { newValue -> eventSummary.value = newValue },
            label = {
                Text(text = "Summary of event...")
            }
        )
        OutlinedTextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        datePickerDialog.show()
                    }
                }
            ,
            value = eventDate.value,
            onValueChange = {},
            label = {
                Text(text = "yyyy/mm/dd")
            },
        )
        Button(onClick = {
            val event = Event(
                name=eventSummary.value,
                date=eventDate.value
            )

            onSubmitRequest(event)
        }) {
            Text(text="Save")
        }
    }
}

@Preview
@Composable
fun PreviewRegisterEvent() {
    RegisterEvent()
}