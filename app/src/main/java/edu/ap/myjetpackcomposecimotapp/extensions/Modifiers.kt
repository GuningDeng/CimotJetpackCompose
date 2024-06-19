package edu.ap.myjetpackcomposecimotapp.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@OptIn(ExperimentalStdlibApi::class)
fun Modifier.autoCloseKeyboard() : Modifier = composed {
    val keyboardController = LocalSoftwareKeyboardController.current
    pointerInput(this){
        detectTapGestures(
            onPress = {
                keyboardController?.hide()
            }
        )
    }
}

/**
 *
 */
fun Modifier.click(
    onClick: () -> Unit
) : Modifier = composed {
    this.clickable(
        indication = null,
        onClick = onClick,
        interactionSource = remember {
            MutableInteractionSource()
        }
    )
}