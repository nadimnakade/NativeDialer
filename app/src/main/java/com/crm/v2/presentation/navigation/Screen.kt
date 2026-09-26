package com.crm.v2.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object PinSetup : Screen("pin_setup")
    data object Dashboard : Screen("dashboard")
    data object Leads : Screen("leads")
    data object LeadDetail : Screen("lead_detail/{key}") {
        fun createRoute(key: String) = "lead_detail/$key"
    }
    data object AddLead : Screen("add_lead")
    data object Dialer : Screen("dialer")
    data object DialerAuto : Screen("dialer/auto")
    data object DialerManual : Screen("dialer/manual")
    data object ActiveCall : Screen("active_call/{number}") {
        fun createRoute(number: String) = "active_call/$number"
    }
    data object UpdateStatus : Screen("update_status/{key}/{name}/{contact}") {
        fun createRoute(key: String, name: String, contact: String) = "update_status/$key/$name/$contact"
    }
    data object Contacts : Screen("contacts")
    data object ContactDetail : Screen("contact_detail/{id}") {
        fun createRoute(id: String) = "contact_detail/$id"
    }
    data object FollowUps : Screen("follow_ups")
    data object SameDayFollowUps : Screen("sameday_follow_ups")
    data object OneMinCalls : Screen("onemin_calls")
    data object RingingCalls : Screen("ringing_calls")
    data object Reports : Screen("reports")
    data object Performance : Screen("performance")
    data object Rankings : Screen("rankings")
    data object Attendance : Screen("attendance")
    data object Disbursal : Screen("disbursal")
    data object PickupList : Screen("pickup_list")
    data object PickupDetail : Screen("pickup_detail/{key}") {
        fun createRoute(key: String) = "pickup_detail/$key"
    }
    data object AddPickup : Screen("add_pickup")
    data object DailyLogin : Screen("daily_login")
    data object DailyLoginList : Screen("daily_login_list")
    data object Documents : Screen("documents")
    data object DocumentUpload : Screen("document_upload")
    data object Notifications : Screen("notifications")
    data object Settings : Screen("settings")
    data object Profile : Screen("profile")
    data object CallHistory : Screen("call_history")
    data object CallRecordings : Screen("call_recordings")
    data object AgentStatus : Screen("agent_status")
    data object Whistleblower : Screen("whistleblower")
    data object Payslip : Screen("payslip")
    data object Search : Screen("search")
    data object SearchApplication : Screen("search_application")
}
