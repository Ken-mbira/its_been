package com.opencanvas.itsbeen.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opencanvas.itsbeen.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    queryParam: String = "",
    emitNewQuery: (query: String) -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val textInput = remember { mutableStateOf(queryParam) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = modifier
                .weight(1f)
                .focusRequester(focusRequester),
            value = queryParam,
            onValueChange = { newValue ->
                emitNewQuery(newValue)
            },
            label = {
                Text(
                    text="Search",
                    color= if(isSystemInDarkTheme()){
                        Color.White
                    }else{
                        Color.Black
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if(isSystemInDarkTheme()){
                    Color.DarkGray
                }else{
                    Color.LightGray
                },
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(25.dp),
        )
        IconButton(
            onClick = {
                focusManager.clearFocus()
                emitNewQuery("")
            },
            modifier = modifier.padding(start=10.dp, end=10.dp)
        ) {
            Icon(
                imageVector = if(queryParam.isBlank()){
                    Icons.Rounded.Search
                }else{
                     Icons.Rounded.Close
                },
                contentDescription = stringResource(R.string.search_icon),
                modifier = modifier
                    .size(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSince() {
    SearchBar()
}