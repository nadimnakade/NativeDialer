package com.crm.v2.common.constants

object ApiConstants {
    const val API_BASE_URL = "https://1up.co.in/DialerAPI/api/"
    const val DIALER_WS_URL = "http://160.250.28.172:5031/Dialer_WS/"

    const val CONNECT_TIMEOUT_SECONDS = 30L
    const val READ_TIMEOUT_SECONDS = 30L
    const val WRITE_TIMEOUT_SECONDS = 30L
    const val MAX_RETRIES = 3

    object Auth {
        const val LOGIN = "UpdateStatus/ValidateLogin"
        const val LOGIN_MOBILE = "UpdateStatus/ValidateLogin"
        const val USER_STATUS = "Pickup/user-status"
    }

    object Pickup {
        const val GET_DATA = "Pickup/GetPickupData"
        const val GET_ENTRY_DATA = "Pickup/GetPickupEntryData"
        const val UPDATE_STATUS = "Pickup/UpdateStatus"
        const val INSERT = "Pickup/InsertPickup"
        const val DASHBOARD_COUNTER = "Pickup/DashboardCounter"
        const val DIALER_LIVE = "Pickup/DialerLive"
        const val DIALER_LIVE_INDV = "Pickup/DialerLiveIndv"
        const val LIVE_COUNT = "Pickup/FetchLiveCount"
        const val TEAM_CALL_LOG = "Pickup/GetTeamCallLog"
        const val ATTENDANCE = "Pickup/GetAttendance"
        const val INCENTIVE = "Pickup/GetIncentive"
        const val APPLICATIONS = "Pickup/GetApplications"
    }

    object Dialer {
        const val GET_DATA = "Pickup/GetDialerData"
        const val GET_NOT_UPDATED = "Pickup/GetDialerNotUpdatedData"
        const val UPDATE_STATUS = "Pickup/UpdateDialingStatus"
        const val UPDATE = "Dialer/update"
        const val INSERT_LEAD = "Pickup/InsertLeadInfo"
        const val ADD_LEAD = "Pickup/AddLead"
        const val GET_LEAD = "Pickup/GetLead"
        const val MANAGE_LEAD = "Pickup/ManageLead"
        const val LEAD_COUNT = "Pickup/GetLeadCount"
        const val LEAD_FOLLOWUP = "Pickup/GetLeadnFollowupData"
    }

    object CallLog {
        const val SAVE = "Pickup/FnSaveCallLogs"
        const val SAVE_ALL = "Pickup/FnSaveCallLogsAll"
        const val SAVE_INDV = "Pickup/FnSaveCallLogsIndv"
        const val SAVE_RECORDING = "Pickup/SaveRecording"
    }

    object DailyLogin {
        const val INSERT = "Pickup/InsertDailyLogins"
        const val LIST = "Pickup/GetDailyLoginList"
    }

    object Documents {
        const val SAVE = "Pickup/SaveDocuments"
        const val GET_CLIENTS = "Pickup/GetCallerClients"
        const val GET_DOCUMENTS = "Pickup/GetClientDocuments"
    }

    object Email {
        const val SEND = "Pickup/SendEmail"
    }

    object Pdf {
        const val GENERATE = "Pickup/GeneratePdf"
    }

    object Call {
        const val PLACE = "Call/Place"
        const val HANGUP = "Call/Hangup"
        const val STATUS = "Call/Status"
    }

    object CloudConnect {
        const val BASE_URL = "https://crm2.cloud-connect.in/ccpl_api/v1.4/"
        const val CLICK_2_CALL = "api/info/click2call"
        const val TOKEN_ID = "varsvZnOX5Z7Ibd2"
        const val EXTENSION_NUMBER = "701"
        const val EXTENSION_PASSWORD = "b147a109a38e4d3137e7442e54dd0fd0"
    }

    object Sip {
        const val SERVER = "sip2.cloud-connect.in"
        const val PORT = "7065"
        const val USERNAME = "102724701"
        const val PASSWORD = "Tejas@001"
        const val REALM = "sip2.cloud-connect.in"
    }
}
