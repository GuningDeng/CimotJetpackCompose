package edu.ap.myjetpackcomposecimotapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    val userId: Long,
    val name: String
): Parcelable