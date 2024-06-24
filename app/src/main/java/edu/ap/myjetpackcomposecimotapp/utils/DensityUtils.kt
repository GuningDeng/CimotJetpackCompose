package edu.ap.myjetpackcomposecimotapp.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object DensityUtils {
    fun px2dp(px: Int, density: Float): Dp {
        return  (px / density).dp
    }
}