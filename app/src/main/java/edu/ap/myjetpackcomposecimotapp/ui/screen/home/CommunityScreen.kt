package edu.ap.myjetpackcomposecimotapp.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import edu.ap.myjetpackcomposecimotapp.R
import edu.ap.myjetpackcomposecimotapp.view.CQDivider

@Composable
fun CommunityScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()

    Surface {
        LazyColumn(
            contentPadding = innerPadding,
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        ContextCompat.getColor(context, R.color.nav_bg)
                    )
                )
        ) {
            item {
                CQDivider()
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = "Community",
                        color = Color(ContextCompat.getColor(context, R.color.grey_900)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 45.dp)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }

    }
}