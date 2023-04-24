package com.example.itsbeen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.itsbeen.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val showRegisterEventDialog = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        PeriodDisplay()
        Spacer(modifier = modifier.height(40.dp))
        SinceDisplay()
        EventListDisplay()
        Button(
            onClick = { showRegisterEventDialog.value = true },
            modifier = modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.deep_purple),
                contentColor = Color.White
            )
        ) {
            Text(
                modifier = modifier,
                text= stringResource(R.string.create_new_memory)
            )
        }
        if (showRegisterEventDialog.value) {
            Dialog(onDismissRequest = { showRegisterEventDialog.value = false }) {
                RegisterEvent(onDismissRequest = { showRegisterEventDialog.value = false })
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    HomeScreen()
}