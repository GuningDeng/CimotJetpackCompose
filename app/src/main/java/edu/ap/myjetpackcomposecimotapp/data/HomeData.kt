package edu.ap.myjetpackcomposecimotapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * home bottom nav
 */
data class NavigationItem(
    /**
     * name
     */
    val title: String,
    /**
     * icons
     */
    val icon: ImageVector
)

/**
 * home title
 */
val titles = listOf("Spelen", "Ontdekken", "Community", "Profiel")

/**
 * bottom navigation data
 */
val navList = listOf(
    NavigationItem(
        "Spelen",
        Icons.Filled.SportsBaseball
    ),
    NavigationItem(
        "Ontdekken",
        Icons.Filled.Radar
    ),
    NavigationItem(
        "Community",
        Icons.Filled.Sms
    ),
    NavigationItem(
        "Profiel",
        Icons.Outlined.Person
    )
)