package com.crm.v2.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crm.v2.ui.theme.Background
import com.crm.v2.ui.theme.Primary
import com.crm.v2.ui.theme.Surface

data class DrawerItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

val drawerItems = listOf(
    DrawerItem("My Performance", Icons.Filled.BarChart, Screen.Performance.route),
    DrawerItem("Ranking", Icons.Filled.EmojiEvents, Screen.Rankings.route),
    DrawerItem("Add Lead", Icons.Filled.PersonAddAlt1, Screen.AddLead.route),
    DrawerItem("View Lead", Icons.Filled.Leaderboard, Screen.Leads.route),
    DrawerItem("Attendance", Icons.Filled.CalendarMonth, Screen.Attendance.route),
    DrawerItem("Daily Login", Icons.Filled.Login, Screen.DailyLogin.route),
    DrawerItem("Whistle Blower", Icons.Filled.Warning, Screen.Whistleblower.route),
    DrawerItem("Pay Slip", Icons.Filled.Description, Screen.Payslip.route),
    DrawerItem("Profile", Icons.Filled.Person, Screen.Profile.route),
    DrawerItem("Follow Up", Icons.Filled.FollowTheSigns, Screen.FollowUps.route),
    DrawerItem("Same Day Follow Up", Icons.Filled.EventRepeat, Screen.SameDayFollowUps.route),
    DrawerItem("+1 Minute Calls", Icons.Filled.Timer, Screen.OneMinCalls.route),
    DrawerItem("Ringing", Icons.Filled.PhoneInTalk, Screen.RingingCalls.route),
    DrawerItem("Documents", Icons.Filled.Folder, Screen.Documents.route),
    DrawerItem("Add Documents", Icons.Filled.Upload, Screen.DocumentUpload.route),
    DrawerItem("Call History", Icons.Filled.History, Screen.CallHistory.route),
    DrawerItem("Search Application", Icons.Filled.Apps, Screen.SearchApplication.route),
    DrawerItem("Settings", Icons.Filled.Settings, Screen.Settings.route),
    DrawerItem("Search from Vault", Icons.Filled.ManageSearch, Screen.Search.route),
)

@Composable
fun DrawerContent(
    onNavigate: (String) -> Unit,
    onClose: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color.White),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary)
                    .padding(24.dp),
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "3C",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "360 Connect",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Menu",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp,
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFE2E8F0))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                items(drawerItems) { item ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = item.label,
                                color = Color(0xFF1E293B),
                                fontSize = 14.sp,
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = Color(0xFF64748B),
                                modifier = Modifier.size(22.dp),
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onNavigate(item.route)
                                onClose()
                            },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.Transparent,
                        ),
                    )
                }
            }
        }
    }
}
