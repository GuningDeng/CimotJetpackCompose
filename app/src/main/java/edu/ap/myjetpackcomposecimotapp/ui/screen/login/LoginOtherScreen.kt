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
import androidx.compose.material3.TextFieldDefaults
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
import edu.ap.myjetpackcomposecimotapp.activity.MainActivity
import edu.ap.myjetpackcomposecimotapp.extensions.autoCloseKeyboard
import edu.ap.myjetpackcomposecimotapp.ui.route.LOGIN_PHONE
import edu.ap.myjetpackcomposecimotapp.ui.screen.ProcessDialogComponent
import edu.ap.myjetpackcomposecimotapp.utils.toast
import edu.ap.myjetpackcomposecimotapp.view.CQDivider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginOtherScreen(navController: NavHostController) {
    val context = LocalContext.current as Activity
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    var isPhone by remember {
        mutableStateOf(true)
    }
    var phonetext by remember {
        mutableStateOf("")
    }
    var pwdOrPhoneText by remember {
        mutableStateOf("")
    }
    val loading = remember {
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
                    title = { },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
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
                         *
                         */
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        /**
                         * login by phone
                         */
                        item {
                            Box(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isPhone) "login by phone" else "/login by email",
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
                                    readOnly = isPhone,
                                    value = phonetext,
                                    onValueChange = {
                                        phonetext = it
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                        focusedBorderColor = Color.White,
                                        unfocusedBorderColor = Color.White
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    leadingIcon = {
                                      Text(
                                          text = if (isPhone) "country/region" else "account",
                                          fontSize = 18.sp,
                                          modifier = Modifier
                                              .width(if (isPhone) 120.dp else 90.dp)
                                      )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(
                                            text = if (!isPhone) "enter Facebook/..." else "(+32)",
                                            fontSize = 16.sp,
                                            color = Color(0xff888888)
                                        )
                                    }
                                )
                            }
                        }

                        /**
                         * underline of phone
                         */
                        item {
                            Box(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                            ) {
                                CQDivider(thickness = 1.dp)
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
                                    value = pwdOrPhoneText,
                                    onValueChange = {
                                        pwdOrPhoneText = it
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(

                                        focusedBorderColor = Color.Green,
                                        unfocusedBorderColor = Color(0xff888888),
                                        cursorColor = Color.Green
                                    ),
                                    textStyle = TextStyle(
                                      fontSize = 16.sp
                                    ),
                                    leadingIcon = {
                                        Text(
                                            text = if (isPhone) "Phone number" else "Password",
                                            fontSize = 18.sp,
                                            modifier = Modifier
                                                .width(if (isPhone) 120.dp else 90.dp)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(
                                            text = if (isPhone) "Please fill in your phone number" else "Please fill in the password",
                                            fontSize = 16.sp,
                                            color = Color(0xff888888),
                                        )
                                    }
                                )
                            }
                        }
                        item {
                            Text(
                                text = if (!isPhone) "Log in by phone number" else "email/...",
                                fontSize = 16.sp,
                                color = Color(0xff5F6594),
                                modifier = Modifier
                                    .padding(15.dp)
                                    .clickable {
                                        isPhone = !isPhone
                                    }

                            )
                        }
                        item {
                            if (loading.value) {
                                ProcessDialogComponent(
                                    loading = loading,
                                    content = "logging in..."
                                )
                            }
                        }
                    }

                    /**
                     * bottom layout
                     */
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(bottom = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isPhone) "This phone number for verify login" else "email/... verify login",
                                    fontSize = 12.sp,
                                    color = Color(0xff888888)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Agree",
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
                                            if (phonetext == "" && !isPhone) {
                                                context.toast("Please fill in account")
                                                return@clickable
                                            }
                                            if (pwdOrPhoneText == "") {
                                                context.toast(if (isPhone) "Please fill in phone number" else "Pleas fill in password")
                                                return@clickable
                                            }
                                            if (isPhone) {
                                                // navigate to log in by phone page
                                                navController.navigate(
                                                    "$LOGIN_PHONE/$pwdOrPhoneText"
                                                )
                                            } else {
                                                loading.value = true
                                                scope.launch {
                                                    delay(2000)
                                                    loading.value = false
                                                    MainActivity.navigate(context)
                                                }
                                            }
                                        },
                                    textAlign = TextAlign.Center
                                )
                            }

                            /**
                             *
                             */
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp)
                            ) {
                                Text(
                                    text = "Get new password",
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
                                        .background(Color(0xff888888))
                                )
                                Text(
                                    text = "More",
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