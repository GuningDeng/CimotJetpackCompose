package edu.ap.myjetpackcomposecimotapp.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.PersonalVideo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import edu.ap.myjetpackcomposecimotapp.R
import edu.ap.myjetpackcomposecimotapp.extensions.overScrollVertical
import edu.ap.myjetpackcomposecimotapp.extensions.parabolaScrollEasing
import edu.ap.myjetpackcomposecimotapp.extensions.rememberOverscrollFlingBehavior
import edu.ap.myjetpackcomposecimotapp.utils.DensityUtils.px2dp
import edu.ap.myjetpackcomposecimotapp.view.CQDivider
import edu.ap.myjetpackcomposecimotapp.viewmodel.MatchViewModel
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchScreen(innerPadding: PaddingValues, viewModel: MatchViewModel = MatchViewModel(), onChangeVisible: (visible: Boolean) -> Unit) {
    val context = LocalContext.current

    /**
     * status bar height
     */
    val statusBarHeight = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    val scrollState = rememberLazyListState()

    var offset by remember { mutableFloatStateOf(0f) }

    val fullHeight = with(LocalContext.current) {
        resources.displayMetrics.heightPixels
    }
    val density = LocalDensity.current.density

    val springStiff by remember { mutableFloatStateOf(Spring.StiffnessLow) }
    val springDamp by remember { mutableFloatStateOf(Spring.DampingRatioLowBouncy) }
    val dragP by remember { mutableFloatStateOf(50f) }

    var visible by remember { mutableStateOf(false) }

    /***  */
    var scrollPercent by remember { mutableFloatStateOf(0f) }
    /**  */
    var ballSize by remember { mutableFloatStateOf(0f) }
    /**  */
    val target = fullHeight / 4
    val backgroundColor = Color(0xffEDEDED)
    /**  */
    var offsetX by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .padding(top = statusBarHeight)
            .fillMaxSize()
    ) {
        /*
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .alpha(scrollPercent)
                .background(Color(0xff1B1B2B))
        ) {
            MiniProgramScreen(
                contentPadding = innerPadding,
                popBackStack = {
                    visible = false
                    scrollPercent = 0f
                }
            )
        }
        */

        /** mask*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(if (scrollPercent < 0.9) backgroundColor else Color.Transparent),
        )
        
        /** three points animation */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .height(px2dp((offset.toInt()), density))
                .alpha(1 - scrollPercent)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(if (scrollPercent > 0.15f) (if (scrollPercent > 0.3f) 6.dp else 10.dp) else ballSize.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff303030))
            )
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            if (scrollPercent > 0.15f) -offsetX.roundToInt() else 0,
                            0
                        )
                    }
                    .size(6.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xff303030))
            )
            Box(
                modifier = Modifier
                    .offset { IntOffset(if (scrollPercent > 0.15f) offsetX.roundToInt() else 0, 0) }
                    .size(6.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xff303030))
            )
        }
        
        AnimatedVisibility(
            visible = !visible,
            enter = slideInVertically(initialOffsetY = {fullHeight}),
            exit = slideOutVertically(targetOffsetY = {fullHeight})
        ) {
            LazyColumn(
                contentPadding = innerPadding,
                state = scrollState,
                modifier = Modifier
                    .overScrollVertical(
                        isStartScroll = true,
                        isEndScroll = false,
                        nestedScrollToParent = false,
                        scrollEasing = { x1, x2 -> parabolaScrollEasing(x1, x2, dragP) },
                        springStiff = springStiff,
                        springDamp = springDamp,
                        scrollOffset = { x3 ->
                            offset = x3
                            if (offset > target) {
                                visible = true
                                scrollPercent = 1.0f
                            } else if (!visible) {
                                scrollPercent = offset / target
                            }
                            scrollPercent = if (scrollPercent < 0f) 0.0f else scrollPercent
                            ballSize = scrollPercent * 70
                            println("===offset:$offset ====visible:$visible ====scrollPercent:$scrollPercent")
                            onChangeVisible(visible)
                            offsetX = scrollPercent * 100
                        })
                    .alpha(1 - scrollPercent)
                    .background(Color.White),
                flingBehavior = rememberOverscrollFlingBehavior {
                    scrollState
                }
            ) {
                stickyHeader { 
                    TopAppBar(context)
                }
                item { 
                    CQDivider()
                }
                item { 
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(Color(0xFFEDEDED))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(40.dp, 0.dp, 35.dp, 0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PersonalVideo, 
                                contentDescription = null,
                                modifier = Modifier.size(23.dp),
                                tint = Color(
                                    ContextCompat.getColor(context, R.color.gray_600)
                                )
                            )
                            Text(
                                text = "U bent ingelogd",
                                color = Color(ContextCompat.getColor(context, R.color.gray_600)),
                                fontSize = 14.sp,
                                modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                    }
                }
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
                            text = "Matches",
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

}

@Composable
private fun TopAppBar(context : Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(ContextCompat.getColor(context, R.color.nav_bg)))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Spelen",
                maxLines = 1,
                fontSize = 16.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(
                            ContextCompat.getColor(
                                context,
                                R.color.grey_900
                            )
                        )
                    )
                }

                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.AddCircleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = Color(
                            ContextCompat.getColor(
                                context,
                                R.color.grey_900
                            )
                        )
                    )
                }
            }
        }

    }
}