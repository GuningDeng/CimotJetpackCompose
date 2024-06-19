package edu.ap.myjetpackcomposecimotapp.ui.screen.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import edu.ap.myjetpackcomposecimotapp.R
import edu.ap.myjetpackcomposecimotapp.activity.MainActivity
import edu.ap.myjetpackcomposecimotapp.data.cimotLogo
import edu.ap.myjetpackcomposecimotapp.data.myAvatar
import edu.ap.myjetpackcomposecimotapp.extensions.click
import edu.ap.myjetpackcomposecimotapp.ui.route.LOGIN_OTHER
import edu.ap.myjetpackcomposecimotapp.ui.screen.ModalBottomSheetDialog
import edu.ap.myjetpackcomposecimotapp.ui.screen.ProcessDialogComponent
import edu.ap.myjetpackcomposecimotapp.ui.theme.Grey900
import edu.ap.myjetpackcomposecimotapp.ui.theme.Purple40
import edu.ap.myjetpackcomposecimotapp.utils.toast
import edu.ap.myjetpackcomposecimotapp.view.CQDivider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current as Activity
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)

    val password = ""
    var pwdText by remember { mutableStateOf(password) }
    val loading = remember { mutableStateOf(false) }
    var isFocus by remember { mutableStateOf(false) }
    var isPwdAuth by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = androidx.compose.material.rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    Surface(
        Modifier
            .fillMaxSize()
            .background(androidx.compose.material3.MaterialTheme.colorScheme.background)

    ) {
        Scaffold(
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffEDEDED))
                        .padding(innerPadding)
                ) {
                    LazyColumn(contentPadding = innerPadding) {
                        /**
                         * space between nav bar and image
                         */
                        item {
                            Spacer(modifier = Modifier.height(50.dp))
                        }
                        /**
                         * image
                         */
                        item {
                            Box(
                                modifier = Modifier
                                    .height(70.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = rememberCoilPainter(request = cimotLogo),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(160.dp)
                                )
                            }
                        }
                        /**
                         * phone
                         */
                        item {
                            Box(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.material3.Text(
                                    text = "9990000000",
                                    fontSize = 18.sp
                                )
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
                                    .wrapContentHeight(),
                            ) {
                                androidx.compose.material3.TextField(
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
                                        androidx.compose.material3.Text(
                                            text = if (isPwdAuth) "Password" else "Verification code",
                                            fontSize = 18.sp,
                                            modifier = Modifier.width(90.dp)
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged {
                                            isFocus = when {
                                                it.isFocused -> true
                                                else -> false
                                            }
                                        },
                                    placeholder = {
                                        androidx.compose.material3.Text(
                                            text = if (isPwdAuth) "Please fill in password" else "Please fill in the verification code",
                                            fontSize = 16.sp,
                                            color = Color(0xff888888),
                                        )
                                    }
                                )
                            }
                        }
                        /**
                         * get verification code
                         */
                        item {
                            if (!isPwdAuth) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 110.dp, end = 15.dp, bottom = 4.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    androidx.compose.material3.Text(
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
                        /**
                         * Underline of phone number
                         */
                        item {
                            Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                                CQDivider(
                                    thickness = 1.dp,
                                    colorId = if (isFocus) R.color.grey_900 else R.color.gray_300
                                )

                            }
                        }
                        item {
                            androidx.compose.material3.Text(
                                text = if (isPwdAuth) "Log in by verification code" else "Log in by password",
                                fontSize = 14.sp,
                                color = Color(0xff5F6594),
                                modifier = Modifier
                                    .padding(15.dp)
                                    .clickable {
                                        isPwdAuth = !isPwdAuth
                                    }
                            )
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(top = 40.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.material3.Text(
                                    text = "Log in",
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .wrapContentWidth()
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(Grey900)
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 120.dp,
                                            end = 120.dp
                                        )
                                        .click {
                                            if (pwdText == "") {
                                                context.toast(if (isPwdAuth) "Please fill in the password" else "Please fill in the verification code")
                                                return@click
                                            }
                                            if (pwdText != "123456") {
                                                context.toast(if (isPwdAuth) "Incorrect password" else "Incorrect verification code")
                                                return@click
                                            }
                                            loading.value = true
                                            scope.launch {
                                                delay(2000)
                                                loading.value = false
                                                MainActivity.navigate(context)
                                                //EasyDataStore.putData("password", pwdText)
                                            }
                                        },
                                    textAlign = TextAlign.Center
                                )
                            }
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
                     * bottom layout（forget the password）
                     */
                    /**
                     * bottom layout（forget the password）
                     */
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            /**
                             * the dividing line in the middle
                             */
                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(top = 60.dp)
                            ) {
                                Text(
                                    text = "Forget password",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(top = 15.dp, end = 15.dp),
                                )
                                Box(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(0.5.dp)
                                        .padding(top = 15.dp, bottom = 8.dp)
                                        .background(Color(0xff888888))
                                )
                                Text(
                                    text = "Freeze account",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(top = 15.dp, start = 15.dp, end = 15.dp),
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
                                        .wrapContentWidth()
                                        .padding(start = 15.dp, top = 15.dp)
                                        .click {
                                            /**
                                             * show bottom sheet
                                             */
                                            scope.launch { modalBottomSheetState.show() }
                                        },
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
                /**
                 * show more bottom sheet
                 */
                ModalBottomSheetDialog(
                    titles = listOf("Log in to another accounts", "register", "Security issues", "Feedback"),
                    coroutineScope = scope,
                    modalBottomSheetState = modalBottomSheetState,
                    onSelect = { index, title ->
                        Log.d("LoginScreen", "index====$index  title=====$title")
                        /**
                         * navigate to another accounts
                         */
                        if (index == 0) {
                            navController.navigate(LOGIN_OTHER)
                        }
                    }
                )
            }
        )
    }
}