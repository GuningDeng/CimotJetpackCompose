package edu.ap.myjetpackcomposecimotapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import edu.ap.myjetpackcomposecimotapp.view.CQDivider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProcessDialogComponent(
    loading: MutableState<Boolean>,
    content: String
) {
    if (loading.value) {
        Dialog(onDismissRequest = { loading.value = false }) {
            Box(
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xff4C4C4C))
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(30.dp)
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        strokeWidth = 3.dp
                    )
                    Spacer(modifier = Modifier.requiredHeight(30.dp))
                    Text(
                        text = content,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetDialog(
    titles: List<String>,
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    onSelect: (index: Int, title: String) -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        ),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
            ) {
                titles.forEachIndexed { index, title ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .clickable {
                                onSelect(index, title)
                                coroutineScope.launch { modalBottomSheetState.hide() }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    CQDivider()
                }
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(Color(0xffF7F7F7))
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancel",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .clickable {
                                coroutineScope.launch { modalBottomSheetState.hide() }
                            },
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    ) { }
}