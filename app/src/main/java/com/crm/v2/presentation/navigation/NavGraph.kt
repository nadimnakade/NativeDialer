package com.crm.v2.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crm.v2.presentation.ui.auth.LoginScreen
import com.crm.v2.presentation.ui.callhistory.CallHistoryScreen
import com.crm.v2.presentation.ui.contacts.ContactsScreen
import com.crm.v2.presentation.ui.dailylogin.DailyLoginScreen
import com.crm.v2.presentation.ui.dashboard.DashboardScreen
import com.crm.v2.presentation.ui.dialer.ActiveCallScreen
import com.crm.v2.presentation.ui.dialer.DialerScreen
import com.crm.v2.presentation.ui.dialer.UpdateStatusScreen
import com.crm.v2.presentation.ui.documents.DocumentListScreen
import com.crm.v2.presentation.ui.followup.FollowUpScreen
import com.crm.v2.presentation.ui.leads.AddLeadScreen
import com.crm.v2.presentation.ui.leads.LeadDetailScreen
import com.crm.v2.presentation.ui.leads.LeadListScreen
import com.crm.v2.presentation.ui.pickup.PickupDetailScreen
import com.crm.v2.presentation.ui.pickup.PickupListScreen
import com.crm.v2.presentation.ui.reports.ReportsScreen
import com.crm.v2.presentation.ui.agentstatus.AgentStatusScreen
import com.crm.v2.presentation.ui.callrecordings.CallRecordingsScreen
import com.crm.v2.presentation.ui.dailyloginlist.DailyLoginListScreen
import com.crm.v2.presentation.ui.documentupload.DocumentUploadScreen
import com.crm.v2.ui.theme.GlassBorder
import com.crm.v2.ui.theme.GlassSurface
import com.crm.v2.ui.theme.Primary
import com.crm.v2.ui.theme.Background
import com.crm.v2.ui.theme.Outline
import com.crm.v2.ui.theme.Surface as AppSurface
import com.crm.v2.presentation.ui.onemincalls.OneMinCallsScreen
import com.crm.v2.presentation.ui.payslip.PayslipScreen
import com.crm.v2.presentation.ui.ringingcalls.RingingCallsScreen
import com.crm.v2.presentation.ui.samedayfollowups.SameDayFollowUpsScreen
import com.crm.v2.presentation.ui.settings.ProfileScreen
import com.crm.v2.presentation.ui.settings.SettingsScreen
import com.crm.v2.presentation.ui.search.SearchScreen
import com.crm.v2.presentation.ui.searchapplication.SearchApplicationScreen
import com.crm.v2.presentation.ui.whistleblower.WhistleblowerScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val bottomNavRoutes = listOf(
        Screen.Dashboard.route, Screen.Leads.route, Screen.Dialer.route,
        Screen.Reports.route,
    )
    val showBottomBar = currentRoute in bottomNavRoutes
    val showDrawer = currentRoute in listOf(Screen.Dashboard.route, Screen.Leads.route, Screen.Reports.route)
    val hiddenRoutes = listOf(Screen.Login.route, Screen.Dashboard.route, Screen.Dialer.route,
        Screen.Leads.route, Screen.Reports.route)

    ModalNavigationDrawer(
        drawerState = drawerState,
            drawerContent = {
            DrawerContent(
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true; restoreState = true
                    }
                },
                onClose = { scope.launch { drawerState.close() } },
            )
        },
        gesturesEnabled = showDrawer,
        scrimColor = Color.Black.copy(alpha = 0.5f),
    ) {
    Scaffold(
        topBar = {
            if (currentRoute == Screen.Dashboard.route) {
                CenterAlignedTopAppBar(
                    title = { Text("360 Connect", color = Color(0xFF1E293B)) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color(0xFF1E293B),
                        actionIconContentColor = Color(0xFF1E293B),
                    ),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color(0xFF1E293B))
                        }
                    },
                )
            } else if (currentRoute !in hiddenRoutes) {
                    val title = currentRoute?.let { route ->
                    when {
                        route.startsWith("lead_detail") -> "Lead Detail"
                        route.startsWith("active_call") -> "Active Call"
                        route.startsWith("update_status") -> "Update Status"
                        route.startsWith("add_lead") -> "Add Lead"
                        route.startsWith("follow_ups") -> "Follow-ups"
                        route.startsWith("settings") -> "Settings"
                        route.startsWith("profile") -> "Profile"
                        route.startsWith("documents") -> "Documents"
                        route.startsWith("pickup") -> "Pickups"
                        route.startsWith("daily_login") -> "Daily Login"
                        route.startsWith("call_history") -> "Call History"
                        route.startsWith("call_recordings") -> "Call Recordings"
                        route.startsWith("whistleblower") -> "Whistleblower"
                        route.startsWith("payslip") -> "Pay Slip"
                        route.startsWith("search_application") -> "Search Application"
                        route.startsWith("search") -> "Search from Vault"
                        else -> route.replace("_", " ").replaceFirstChar { it.uppercase() }
                    }
                } ?: "360 Connect"
                CenterAlignedTopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                    NavigationBar(
                        containerColor = Color.White,
                        contentColor = Color(0xFF1E293B),
                    ) {
                        bottomNavItems.forEach { item ->
                            val selected = navBackStackEntry?.destination?.hierarchy?.any {
                                it.route == item.route
                            } == true

                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label
                                    )
                                },
                                label = { Text(item.label) },
                                selected = selected,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true; restoreState = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Primary,
                                    selectedTextColor = Primary,
                                    unselectedIconColor = Color(0xFF94A3B8),
                                    unselectedTextColor = Color(0xFF94A3B8),
                                    indicatorColor = Primary.copy(alpha = 0.1f),
                                ),
                            )
                        }
                    }
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable(Screen.Login.route) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) { popUpTo(Screen.Login.route) { inclusive = true } }
                })
            }

            // Core tabs
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    onNavigateToDialer = { navController.navigate(Screen.Dialer.route) },
                    onNavigateToLeads = { navController.navigate(Screen.AddLead.route) },
                    onNavigateToFollowUps = { navController.navigate(Screen.FollowUps.route) },
                    onNavigateToReports = { navController.navigate(Screen.Reports.route) },
                )
            }

            composable(Screen.Leads.route) {
                LeadListScreen(
                    onLeadClick = { key -> navController.navigate(Screen.LeadDetail.createRoute(key)) },
                    onAddLead = { navController.navigate(Screen.AddLead.route) },
                )
            }

            composable(Screen.AddLead.route) {
                AddLeadScreen(
                    onSuccess = { navController.navigate(Screen.Leads.route) { popUpTo(Screen.Leads.route) } },
                    onBack = { navController.popBackStack() },
                )
            }

            composable(Screen.LeadDetail.route, arguments = listOf(navArgument("key") { type = NavType.StringType })) {
                LeadDetailScreen(
                    onCall = { navController.navigate(Screen.ActiveCall.createRoute(it)) },
                    onBack = { navController.popBackStack() },
                )
            }

            composable(Screen.Dialer.route) {
                DialerScreen(
                    onNavigateBack = { navController.popBackStack() },
                )
            }

            composable(Screen.ActiveCall.route, arguments = listOf(navArgument("number") { type = NavType.StringType })) { entry ->
                val number = entry.arguments?.getString("number") ?: ""
                ActiveCallScreen(
                    number = number,
                    callState = com.crm.v2.telephony.CallState.Connected,
                    onHangup = { navController.popBackStack() },
                    onMute = {}, onSpeaker = {}, onHold = {},
                    onNavigateToUpdateStatus = { navController.popBackStack() },
                )
            }

            composable(
                Screen.UpdateStatus.route,
                arguments = listOf(
                    navArgument("key") { type = NavType.StringType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("contact") { type = NavType.StringType },
                ),
            ) { entry ->
                val key = entry.arguments?.getString("key") ?: ""
                val name = entry.arguments?.getString("name") ?: ""
                val contact = entry.arguments?.getString("contact") ?: ""
                UpdateStatusScreen(
                    leadKey = key,
                    clientName = name,
                    mobileNo = contact,
                    onSave = { navController.navigate(Screen.Dialer.route) { popUpTo(Screen.Dialer.route) } },
                    onCancel = { navController.popBackStack() },
                )
            }

            composable(Screen.Contacts.route) {
                ContactsScreen(onCall = { number -> navController.navigate(Screen.ActiveCall.createRoute(number)) })
            }

            composable(Screen.Reports.route) { ReportsScreen() }

            // Follow-ups
            composable(Screen.FollowUps.route) {
                FollowUpScreen(onCall = { number -> navController.navigate(Screen.ActiveCall.createRoute(number)) })
            }

            // Settings & Profile
            composable(Screen.Settings.route) {
                SettingsScreen(
                    onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                    onLogout = { navController.navigate(Screen.Login.route) { popUpTo(0) { inclusive = true } } },
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onLogout = { navController.navigate(Screen.Login.route) { popUpTo(0) { inclusive = true } } },
                )
            }

            // Documents
            composable(Screen.Documents.route) {
                DocumentListScreen(onUpload = { /* future */ })
            }

            // Pickups
            composable(Screen.PickupList.route) {
                PickupListScreen(
                    onPickupClick = { key -> navController.navigate(Screen.PickupDetail.createRoute(key)) },
                    onAddPickup = { /* future */ },
                )
            }
            composable(Screen.PickupDetail.route, arguments = listOf(navArgument("key") { type = NavType.StringType })) {
                PickupDetailScreen(entry = null, onCall = { number -> navController.navigate(Screen.ActiveCall.createRoute(number)) })
            }

            // Daily Login
            composable(Screen.DailyLogin.route) { DailyLoginScreen() }

            // Call History
            composable(Screen.CallHistory.route) {
                CallHistoryScreen(onCall = { number -> navController.navigate(Screen.ActiveCall.createRoute(number)) })
            }

            // Screens accessible from dashboard overflow or elsewhere
            composable(Screen.SameDayFollowUps.route) {
                SameDayFollowUpsScreen(onCall = { navController.navigate(Screen.ActiveCall.createRoute(it)) })
            }
            composable(Screen.OneMinCalls.route) { OneMinCallsScreen() }
            composable(Screen.RingingCalls.route) {
                RingingCallsScreen(onCall = { navController.navigate(Screen.ActiveCall.createRoute(it)) })
            }
            composable(Screen.DailyLoginList.route) { DailyLoginListScreen() }
            composable(Screen.DocumentUpload.route) { DocumentUploadScreen() }
            composable(Screen.CallRecordings.route) { CallRecordingsScreen() }
            composable(Screen.Whistleblower.route) { WhistleblowerScreen() }
            composable(Screen.Payslip.route) { PayslipScreen() }
            composable(Screen.AgentStatus.route) { AgentStatusScreen() }
            composable(Screen.Search.route) {
                SearchScreen(onCall = { number -> navController.navigate(Screen.ActiveCall.createRoute(number)) })
            }
            composable(Screen.SearchApplication.route) {
                SearchApplicationScreen()
            }

            // Drawer destinations
            composable(Screen.Performance.route) { Placeholder("My Performance") }
            composable(Screen.Rankings.route) { Placeholder("Rankings") }
            composable(Screen.Attendance.route) { Placeholder("Attendance") }
            composable(Screen.AddPickup.route) { Placeholder("Add Pickup") }
            composable(Screen.Disbursal.route) { Placeholder("Disbursal") }
            composable(Screen.Notifications.route) { Placeholder("Notifications") }
        }
    }
    } // ModalNavigationDrawer
}

@Composable
private fun Placeholder(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
    }
}
