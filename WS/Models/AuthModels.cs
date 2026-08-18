using System.Text.Json.Serialization;

namespace WsClient.Models;

public class LoginRequest
{
    [JsonPropertyName("UserName")] public string UserName { get; set; } = "";
    [JsonPropertyName("Password")] public string Password { get; set; } = "";
    [JsonPropertyName("UUID")] public string UUID { get; set; } = "";
}

public class LoginMobileRequest
{
    [JsonPropertyName("MobileNo")] public string MobileNo { get; set; } = "";
    [JsonPropertyName("UDID")] public string UDID { get; set; } = "";
}

public class LoginResponse
{
    [JsonPropertyName("Token")] public string Token { get; set; } = "";
    [JsonPropertyName("Expiration")] public string Expiration { get; set; } = "";
    [JsonPropertyName("UserKey")] public string UserKey { get; set; } = "";
    [JsonPropertyName("EmployeeName")] public string EmployeeName { get; set; } = "";
    [JsonPropertyName("Designation")] public string? Designation { get; set; }
    [JsonPropertyName("Role")] public string? Role { get; set; }
}

public class UserStatus
{
    [JsonPropertyName("isActive")] public bool IsActive { get; set; } = true;
}

public class DashboardCounter
{
    [JsonPropertyName("Rank")] public int? Rank { get; set; }
    [JsonPropertyName("Points")] public double? Points { get; set; }
    [JsonPropertyName("TotalDisbursedAmt")] public double? TotalDisbursedAmt { get; set; }
    [JsonPropertyName("TotalClientConnected")] public int? TotalClientConnected { get; set; }
    [JsonPropertyName("Duration")] public string? Duration { get; set; }
    [JsonPropertyName("DailyLeads")] public int? DailyLeads { get; set; }
    [JsonPropertyName("MonthlyLeads")] public int? MonthlyLeads { get; set; }
    [JsonPropertyName("LoginCount")] public int? LoginCount { get; set; }
    [JsonPropertyName("LoginAmount")] public double? LoginAmount { get; set; }
    [JsonPropertyName("DownTime")] public string? DownTime { get; set; }
    [JsonPropertyName("ConnectivityPercent")] public double? ConnectivityPercent { get; set; }
}

public class DialerLive
{
    [JsonPropertyName("DND")] public int? DND { get; set; }
    [JsonPropertyName("Lead")] public int? Lead { get; set; }
    [JsonPropertyName("FollowUp")] public int? FollowUp { get; set; }
    [JsonPropertyName("NI")] public int? NI { get; set; }
    [JsonPropertyName("NotDoable")] public int? NotDoable { get; set; }
    [JsonPropertyName("WrongNo")] public int? WrongNo { get; set; }
    [JsonPropertyName("Blank")] public int? Blank { get; set; }
    [JsonPropertyName("Interested")] public int? Interested { get; set; }
    [JsonPropertyName("NotContactable")] public int? NotContactable { get; set; }
    [JsonPropertyName("Ringing")] public int? Ringing { get; set; }
    [JsonPropertyName("TotalCount")] public int? TotalCount { get; set; }
    [JsonPropertyName("TotalClientConnected")] public int? TotalClientConnected { get; set; }
    [JsonPropertyName("Duration")] public string? Duration { get; set; }
    [JsonPropertyName("TotalLoginAmt")] public double? TotalLoginAmt { get; set; }
    [JsonPropertyName("TotalLogin")] public int? TotalLogin { get; set; }
    [JsonPropertyName("TotalDisbursedAmt")] public double? TotalDisbursedAmt { get; set; }
    [JsonPropertyName("TotalDisbursedCount")] public int? TotalDisbursedCount { get; set; }
    [JsonPropertyName("DailyLogin")] public int? DailyLogin { get; set; }
    [JsonPropertyName("DailyLoginAmt")] public double? DailyLoginAmt { get; set; }
}
