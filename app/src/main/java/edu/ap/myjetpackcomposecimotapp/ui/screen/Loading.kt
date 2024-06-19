package edu.ap.myjetpackcomposecimotapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFFCCCCCC),
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                strokeWidth = 1.5.dp
            )
            Text(
                text = "loading...",
                fontSize = 13.sp,
                color = Color(0xFF888888),
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    )
            )
        }
    }
}