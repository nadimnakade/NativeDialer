package com.crm.v2.common.constants

import com.crm.v2.data.config.CloudConnectConfig

object ApiConstants {
    const val API_BASE_URL = "https://1up.co.in/NEXUS_API/api/"
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
        const val UPDATE = "Pickup/UpdateDialer"
        const val INSERT_LEAD = "Pickup/AddLead"
        const val ADD_LEAD = "Pickup/InsertLeadInfo"
        const val ADD_LEAD_NEW = "Pickup/InsertLeadInfo_New"
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
        var BASE_URL = "https://crm2.cloud-connect.in/ccpl_api/v1.4/"
        var CLICK_2_CALL = "api/info/click2call"
        var TOKEN_ID = "6JU4KMMIO6B1xzLt"
        var EXTENSION_NUMBER = "802"
        var EXTENSION_PASSWORD = "15e8e7c72ea16ca290930fd7c4db760b"

        fun update(config: CloudConnectConfig) {
            BASE_URL = config.baseUrl
            CLICK_2_CALL = config.click2Call
            TOKEN_ID = config.tokenId
            EXTENSION_NUMBER = config.extensionNumber
            EXTENSION_PASSWORD = config.extensionPassword
        }
    }

    object Sip {
        var SERVER = "sip2.cloud-connect.in"
        var PORT = "7065"
        var USERNAME = "102949802"
        var PASSWORD = "Tejas@123"
        var REALM = "sip2.cloud-connect.in"

        fun update(config: CloudConnectConfig) {
            SERVER = config.sipServer
            PORT = config.sipPort
            USERNAME = config.sipUsername
            PASSWORD = config.sipPassword
            REALM = config.sipRealm
        }
    }

    fun updateFromConfig(config: CloudConnectConfig) {
        CloudConnect.update(config)
        Sip.update(config)
    }
}
