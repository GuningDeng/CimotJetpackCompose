package edu.ap.myjetpackcomposecimotapp.ui.screen.login

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import edu.ap.myjetpackcomposecimotapp.R
import edu.ap.myjetpackcomposecimotapp.activity.MainActivity
import edu.ap.myjetpackcomposecimotapp.extensions.autoCloseKeyboard
import edu.ap.myjetpackcomposecimotapp.ui.screen.ProcessDialogComponent
import edu.ap.myjetpackcomposecimotapp.utils.toast
import edu.ap.myjetpackcomposecimotapp.view.CQDivider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPhoneScreen(navController: NavHostController, phone: String) {
    val context = LocalContext.current as Activity
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    var phoneText by remember { mutableStateOf("+86$phone") }
    var pwdText by remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }
    var isPwdAuth by remember {
        mutableStateOf(true)
    }
    val idFocus by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .autoCloseKeyboard()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {  }, navigationIcon = {
                        IconButton(onClick = {
                            //go back
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    LazyColumn(contentPadding = innerPadding) {
                        /**
                         * space between navigation and title
                         * **/
                        item {
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                        /***
                         * phone logging
                         * */
                        item {
                            Box(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Login by phone",
                                    fontSize = 20.sp
                                )
                            }
                        }
                        /**
                         * phone input
                         */
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                                    .wrapContentHeight()
                            ) {
                                TextField(
                                    readOnly = true,
                                    value = phoneText,
                                    onValueChange = {
                                        phoneText = it
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    leadingIcon = {
                                        Text(
                                            text = "Phone",
                                            fontSize = 18.sp,
                                            modifier = Modifier.width(90.dp)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )

                            }
                        }
                        /**
                         *
                         */
                        item {
                            Box(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                            ) {
                                CQDivider(thickness = 0.5.dp)
                            }
                        }
                        /**
                         * password input
                         */
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                                    .wrapContentHeight()
                            ) {
                                TextField(
                                    value = pwdText,
                                    onValueChange = {
                                        pwdText = it
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                        cursorColor = Color(0xff5ECC71),
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    leadingIcon = {
                                        Text(
                                            text = if (isPwdAuth) "password" else "verification code",
                                            fontSize = 18.sp,
                                            modifier = Modifier.width(90.dp)
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = if (isPwdAuth) "enter wechat password" else "enter verification code",
                                            fontSize = 16.sp,
                                            color = Color(0xff888888)
                                        )
                                    }
                                )
                            }
                        }
                        item {
                            if (!isPwdAuth) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 110.dp, end = 15.dp, bottom = 4.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = "Get verification code",
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .wrapContentHeight()
                                            .wrapContentWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xffE1E1E1))
                                            .padding(
                                                start = 10.dp,
                                                top = 2.dp,
                                                end = 10.dp,
                                                bottom = 6.dp
                                            )
                                    )
                                }
                            }
                        }
                        item {
                            Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                                CQDivider(thickness = 1.dp, colorId = if (idFocus) R.color.light_green_400 else R.color.gray_300)
                            }
                        }
                        item {
                            Text(
                                text = if (isPwdAuth) "login by sms code" else "login by password",
                                fontSize = 14.sp,
                                color = Color(0xff5F6594),
                                modifier = Modifier
                                    .padding(15.dp)
                                    .clickable {
                                        isPwdAuth = !isPwdAuth
                                    }
                            )
                        }
                        /**
                         * progress box
                         */
                        item {
                            if (loading.value) {
                                ProcessDialogComponent(loading = loading, content = "logging in...")
                            }
                        }
                    }

                    /**
                     * bottom layout
                     */
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Login in",
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .wrapContentWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xff5ECC71))
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 80.dp,
                                            end = 80.dp
                                        )
                                        .clickable {
                                            if (pwdText == "") {
                                                context.toast(if (isPwdAuth) "enter password" else "enter verification code")
                                                return@clickable
                                            }
                                            if (pwdText != "123456") {
                                                context.toast(if (isPwdAuth) "incorrect password " else "incorrect verification code")
                                                return@clickable
                                            }
                                            loading.value = true
                                            scope.launch {
                                                delay(2000)
                                                loading.value = false
                                                MainActivity.navigate(context)
                                            }
                                        },
                                    textAlign = TextAlign.Center
                                )
                            }

                            /**
                             * dividing line in the middle
                             */
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp)
                            ) {
                                Text(
                                    text = "Retrieve password",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.End
                                )
                                Box(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(0.5.dp)
                                        .padding(top = 15.dp, bottom = 8.dp)
                                        .background(Color(0xFF888888))
                                )
                                Text(
                                    text = "more options",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}