package com.opencanvas.itsbeen

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import com.opencanvas.itsbeen.ui.theme.ItsBeenTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItsBeenTheme {
                ItsBeenApp(Modifier)
            }
        }
    }
}