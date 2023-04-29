package com.opencanvas.itsbeen.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import kotlinx.coroutines.flow.filter

const val TAG = "SPINNER_PICKER"

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun SpinnerPicker(
    modifier: Modifier = Modifier,
    items: MutableList<String>,
    emitNewItem: (String) -> Unit = {}
) {
    items.add("")
    val itemHeight = 30.dp

    val listState: LazyListState = rememberLazyListState()
    val selectedIndex: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(listState)

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .filter { !it }
            .collect {
                if(selectedIndex.currentItem?.index != null){
                    val currentItem = items[selectedIndex.currentItem?.index!!]
                    if(currentItem != ""){
                        emitNewItem(currentItem)
//                        Log.i(TAG, currentItem)
                    }
                }
            }
    }

    Box {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .height(itemHeight * 3),
            state = listState,
            contentPadding = PaddingValues(8.dp),
        ) {items(items.count()) {item ->
            if(selectedIndex.currentItem?.index != null){
                if(items[item] == items[selectedIndex.currentItem?.index!!]){
                    Text(
                        text = items[item],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(itemHeight),
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                    )
                } else {
                    Text(
                        text = items[item],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(itemHeight),
                        fontSize = 15.sp,
                    )
                }
            }
        }
        }
    }
}