package edu.ap.myjetpackcomposecimotapp.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import edu.ap.myjetpackcomposecimotapp.R
import edu.ap.myjetpackcomposecimotapp.data.navList
import edu.ap.myjetpackcomposecimotapp.data.titles
import edu.ap.myjetpackcomposecimotapp.extensions.click
import edu.ap.myjetpackcomposecimotapp.ui.theme.Grey900
import edu.ap.myjetpackcomposecimotapp.ui.theme.NavBackground
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    var selectIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val pageState = rememberPagerState(initialPage = 0) {
        navList.size
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var visible by remember {
        mutableStateOf(true)
    }

    /**
     * status bar
     */
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(0xffEDEDED),
        darkIcons = true
    )
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
            topBar = {
                if (selectIndex != 0) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                titles[selectIndex],
                                maxLines = 1,
                                fontSize = 16.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        actions = {
                            if (selectIndex != 3) {
                                IconButton(onClick = {  }) {
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
                                IconButton(onClick = {  }) {
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

                        },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color(
                                ContextCompat.getColor(
                                    context,
                                    if (selectIndex != 3) R.color.nav_bg else R.color.white
                                )
                            ),
                            scrolledContainerColor = Color(
                                ContextCompat.getColor(
                                    context,
                                    if (selectIndex != 3) R.color.nav_bg else R.color.white
                                )
                            ),
                            navigationIconContentColor = Color.White,
                            titleContentColor = Color(
                                ContextCompat.getColor(
                                    context,
                                    R.color.grey_900
                                )
                            ),
                            actionIconContentColor = Color(
                                ContextCompat.getColor(
                                    context,
                                    R.color.grey_900
                                )
                            )
                        )
                    )
                } else {
                    Spacer(modifier = Modifier.size(0.dp))
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    NavigationBar(
                        modifier = Modifier.height(80.dp),
                        contentColor = Color(
                            ContextCompat.getColor(
                                context,
                                if (selectIndex > 1) R.color.white else R.color.nav_bg
                            )
                        )
                    ) {
                        navList.forEachIndexed { index, navigationItem ->
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .click {
                                        selectIndex = index
                                        /**
                                         * click tag and switch to page
                                         */
                                        scope.launch {
                                            pageState.scrollToPage(index)
                                        }
                                    }
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    Icon(
                                        imageVector = navigationItem.icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp),
                                        tint = Color(
                                            if (selectIndex == index) ContextCompat.getColor(
                                                context,
                                                R.color.grey_900
                                            )
                                            else ContextCompat.getColor(
                                                context,
                                                R.color.gray_500
                                            )
                                        )
                                    )
                                    Text(
                                        text = navigationItem.title,
                                        fontSize = 12.sp,
                                        color = Color(
                                            if (selectIndex == index) ContextCompat.getColor(
                                                context,
                                                R.color.grey_900
                                            )
                                            else ContextCompat.getColor(
                                                context,
                                                R.color.gray_500
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            },
            content = { innerPadding ->
                Box {
                    HorizontalPager(
                        state = pageState,
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier.fillMaxSize(),
                        userScrollEnabled = visible
                    ) { page ->
                        when (page) {
                            0 -> MatchScreen(innerPadding = innerPadding, onChangeVisible = { v ->
                                visible= !v
                                systemUiController.setSystemBarsColor(
                                    color = if (visible) Color(0xFFEDEDED) else Color(0xFF1B1B1B),
                                    darkIcons = true
                                )
                            })
                            1 -> FindScreen(innerPadding = innerPadding)
                            2 -> CommunityScreen(innerPadding = innerPadding)
                            3 -> ProfileMineScreen(innerpadding = innerPadding)
                        }
                    }
                    LaunchedEffect(pageState) {
                        snapshotFlow { pageState.currentPage }.collect { page ->
                            selectIndex = page
                            println()

                            visible = true
                            systemUiController.setSystemBarsColor(
                                color = if (page != 3) Color(0xFFEDEDED) else Color.White,
                                darkIcons = true
                            )
                        }
                    }
                }
            }
        )
    }
}