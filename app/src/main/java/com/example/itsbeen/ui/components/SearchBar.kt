package com.example.itsbeen.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itsbeen.R
import com.example.itsbeen.ui.SinceDisplay

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    val textInput = remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = modifier.weight(1f)
                .onFocusChanged { focusState -> isFocused.value = focusState.isFocused },
            value = textInput.value,
            onValueChange = { newValue -> textInput.value = newValue},
            label = {
                if(!isFocused.value){
                    Text(
                        text="Search",
                        color= colorResource(R.color.faded_black),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(R.color.light_grey),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(25.dp),
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = modifier.padding(start=10.dp, end=10.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(R.string.search_icon),
                modifier = modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSince() {
    SearchBar()
}