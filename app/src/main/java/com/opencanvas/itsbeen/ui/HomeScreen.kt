package com.opencanvas.itsbeen.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.opencanvas.itsbeen.ui.components.EventList
import com.opencanvas.itsbeen.ui.components.SearchBar
import com.opencanvas.itsbeen.ui.viewmodels.EventViewModel
import java.time.LocalDate
import com.opencanvas.itsbeen.R

const val DATE_FORMAT = "yyyy/M/d"
const val TAG = "HOME_SCREEN"
@RequiresApi(Build.VERSION_CODES.O)
fun parseStringToLocalDate(dateString: String): LocalDate? {
    val splitDate = dateString.split("/")
    return if(dateString != ""){
        LocalDate.of(splitDate[0].toInt(),splitDate[1].toInt(),splitDate[2].toInt())
    }else{
        null
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    val scrollState = rememberScrollState()
    val showRegisterEventDialog = rememberSaveable { mutableStateOf(false) }

    val eventViewModel: EventViewModel = viewModel(factory = EventViewModel.Factory)
    val eventListState = eventViewModel.eventListState.collectAsState()
    val stagedEventState = eventViewModel.stagedEventState.collectAsState()
    val searchParamState = eventViewModel.eventSearchParam.collectAsState()

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .clickable(
                interactionSource,
                indication = null,
                onClick = {
                    focusManager.clearFocus()
                }
            ),
    ) {
        PeriodDisplay(
            modifier = modifier,
            fromDate = parseStringToLocalDate(stagedEventState.value.date)
        )
        Spacer(modifier = modifier.height(30.dp))
        SinceDisplay(
            modifier = modifier,
            event = stagedEventState.value,
        )
        Spacer(modifier = modifier.height(30.dp))
        Column(
            modifier = modifier.padding(20.dp)
        ) {
            SearchBar(
                modifier = modifier,
                queryParam = searchParamState.value,
                emitNewQuery = { newQuery -> eventViewModel.updateSearchQuery(newQuery)}
            )
            Spacer(modifier = modifier.height(30.dp))
            EventList(
                modifier = modifier,
                eventList = eventListState.value,
                currentQuery = searchParamState.value,
                deleteEvent = { event ->
                    eventViewModel.deleteEvent(event)
                },
                stageEvent = { event ->
                    eventViewModel.stageEvent(event)
                }
            )
        }
        Spacer(modifier = modifier.height(10.dp))
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
                RegisterEvent(
                    onDismissRequest = { showRegisterEventDialog.value = false },
                    onSubmitRequest = { event ->
                        eventViewModel.createEvent(event)
                        showRegisterEventDialog.value = false
                    }
                )
            }
        }
    }
}