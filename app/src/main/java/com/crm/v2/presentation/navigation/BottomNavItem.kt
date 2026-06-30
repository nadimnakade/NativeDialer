package com.crm.v2.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Dashboard",
        route = Screen.Dashboard.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    BottomNavItem(
        label = "Leads",
        route = Screen.Leads.route,
        selectedIcon = Icons.Filled.PeopleAlt,
        unselectedIcon = Icons.Outlined.PeopleAlt,
    ),
    BottomNavItem(
        label = "Dialer",
        route = Screen.Dialer.route,
        selectedIcon = Icons.Filled.Call,
        unselectedIcon = Icons.Outlined.Call,
    ),
    BottomNavItem(
        label = "Contacts",
        route = Screen.Contacts.route,
        selectedIcon = Icons.Filled.Contacts,
        unselectedIcon = Icons.Outlined.Contacts,
    ),
    BottomNavItem(
        label = "Reports",
        route = Screen.Reports.route,
        selectedIcon = Icons.Filled.Assessment,
        unselectedIcon = Icons.Outlined.Assessment,
    ),
)
