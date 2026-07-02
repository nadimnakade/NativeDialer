package com.crm.v2.common.constants;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u00c7\u0002\u0018\u00002\u00020\u0001:\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants;", "", "()V", "BASE_URL", "", "CONNECT_TIMEOUT_SECONDS", "", "MAX_RETRIES", "", "READ_TIMEOUT_SECONDS", "WRITE_TIMEOUT_SECONDS", "Auth", "CallLog", "CloudConnect", "DailyLogin", "Dialer", "Documents", "Email", "Pdf", "Pickup", "Sip", "app_debug"})
public final class ApiConstants {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://1up.co.in/DialerAPI/api/";
    public static final long CONNECT_TIMEOUT_SECONDS = 30L;
    public static final long READ_TIMEOUT_SECONDS = 30L;
    public static final long WRITE_TIMEOUT_SECONDS = 30L;
    public static final int MAX_RETRIES = 3;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.common.constants.ApiConstants INSTANCE = null;
    
    private ApiConstants() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Auth;", "", "()V", "LOGIN", "", "LOGIN_MOBILE", "USER_STATUS", "app_debug"})
    public static final class Auth {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LOGIN = "UpdateStatus/ValidateLogin";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LOGIN_MOBILE = "UpdateStatus/ValidateLogin";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String USER_STATUS = "Pickup/user-status";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Auth INSTANCE = null;
        
        private Auth() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$CallLog;", "", "()V", "SAVE", "", "SAVE_ALL", "SAVE_INDV", "SAVE_RECORDING", "app_debug"})
    public static final class CallLog {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SAVE = "Pickup/FnSaveCallLogs";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SAVE_ALL = "Pickup/FnSaveCallLogsAll";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SAVE_INDV = "Pickup/FnSaveCallLogsIndv";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SAVE_RECORDING = "Pickup/SaveRecording";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.CallLog INSTANCE = null;
        
        private CallLog() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$CloudConnect;", "", "()V", "BASE_URL", "", "CLICK_2_CALL", "EXTENSION_NUMBER", "EXTENSION_PASSWORD", "TOKEN_ID", "app_debug"})
    public static final class CloudConnect {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://crm2.cloud-connect.in/ccpl_api/v1.4/";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String CLICK_2_CALL = "api/info/click2call";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TOKEN_ID = "varsvZnOX5Z7Ibd2";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String EXTENSION_NUMBER = "701";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String EXTENSION_PASSWORD = "b147a109a38e4d3137e7442e54dd0fd0";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.CloudConnect INSTANCE = null;
        
        private CloudConnect() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$DailyLogin;", "", "()V", "INSERT", "", "LIST", "app_debug"})
    public static final class DailyLogin {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String INSERT = "Pickup/InsertDailyLogins";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LIST = "Pickup/GetDailyLoginList";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.DailyLogin INSTANCE = null;
        
        private DailyLogin() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Dialer;", "", "()V", "ADD_LEAD", "", "GET_DATA", "GET_LEAD", "GET_NOT_UPDATED", "INSERT_LEAD", "LEAD_COUNT", "LEAD_FOLLOWUP", "MANAGE_LEAD", "UPDATE", "UPDATE_STATUS", "app_debug"})
    public static final class Dialer {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_DATA = "Pickup/GetDialerData";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_NOT_UPDATED = "Pickup/GetDialerNotUpdatedData";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String UPDATE_STATUS = "Pickup/UpdateDialingStatus";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String UPDATE = "Pickup/UpdateDialer";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String INSERT_LEAD = "Pickup/InsertLeadInfo";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ADD_LEAD = "Pickup/AddLead";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_LEAD = "Pickup/GetLead";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String MANAGE_LEAD = "Pickup/ManageLead";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LEAD_COUNT = "Pickup/GetLeadCount";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LEAD_FOLLOWUP = "Pickup/GetLeadnFollowupData";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Dialer INSTANCE = null;
        
        private Dialer() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Documents;", "", "()V", "GET_CLIENTS", "", "GET_DOCUMENTS", "SAVE", "app_debug"})
    public static final class Documents {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SAVE = "Pickup/SaveDocuments";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_CLIENTS = "Pickup/GetCallerClients";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_DOCUMENTS = "Pickup/GetClientDocuments";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Documents INSTANCE = null;
        
        private Documents() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Email;", "", "()V", "SEND", "", "app_debug"})
    public static final class Email {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SEND = "Pickup/SendEmail";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Email INSTANCE = null;
        
        private Email() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Pdf;", "", "()V", "GENERATE", "", "app_debug"})
    public static final class Pdf {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GENERATE = "Pickup/GeneratePdf";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Pdf INSTANCE = null;
        
        private Pdf() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Pickup;", "", "()V", "APPLICATIONS", "", "ATTENDANCE", "DASHBOARD_COUNTER", "DIALER_LIVE", "DIALER_LIVE_INDV", "GET_DATA", "GET_ENTRY_DATA", "INCENTIVE", "INSERT", "LIVE_COUNT", "TEAM_CALL_LOG", "UPDATE_STATUS", "app_debug"})
    public static final class Pickup {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_DATA = "Pickup/GetPickupData";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GET_ENTRY_DATA = "Pickup/GetPickupEntryData";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String UPDATE_STATUS = "Pickup/UpdateStatus";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String INSERT = "Pickup/InsertPickup";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DASHBOARD_COUNTER = "Pickup/DashboardCounter";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DIALER_LIVE = "Pickup/DialerLive";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DIALER_LIVE_INDV = "Pickup/DialerLiveIndv";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LIVE_COUNT = "Pickup/FetchLiveCount";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TEAM_CALL_LOG = "Pickup/GetTeamCallLog";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ATTENDANCE = "Pickup/GetAttendance";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String INCENTIVE = "Pickup/GetIncentive";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String APPLICATIONS = "Pickup/GetApplications";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Pickup INSTANCE = null;
        
        private Pickup() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/crm/v2/common/constants/ApiConstants$Sip;", "", "()V", "PASSWORD", "", "PORT", "SERVER", "USERNAME", "app_debug"})
    public static final class Sip {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SERVER = "sip2.cloud-connect.in ";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PORT = "7065";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String USERNAME = "701";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PASSWORD = "Tejas@002";
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.common.constants.ApiConstants.Sip INSTANCE = null;
        
        private Sip() {
            super();
        }
    }
}