using System.Text.Json.Serialization;

namespace DialerAPI.Models;

public class DashboardCounterDto
{
    [JsonPropertyName("Duration")] public string? Duration { get; set; }
    [JsonPropertyName("DisbursedAmt")] public string? DisbursedAmt { get; set; }
    [JsonPropertyName("TotalDisbursedAmt")] public string? TotalDisbursedAmt { get; set; }
    [JsonPropertyName("Rank")] public string? Rank { get; set; }
    [JsonPropertyName("Incentive")] public string? Incentive { get; set; }
    [JsonPropertyName("DailyLoginCount")] public string? DailyLoginCount { get; set; }
    [JsonPropertyName("DailyLoginAmt")] public string? DailyLoginAmt { get; set; }
    [JsonPropertyName("DailyLeadCount")] public string? DailyLeadCount { get; set; }
    [JsonPropertyName("MonthlyLeadCount")] public string? MonthlyLeadCount { get; set; }
    [JsonPropertyName("MonthlyLoginAmt")] public string? MonthlyLoginAmt { get; set; }
    [JsonPropertyName("MonthlyLoginCount")] public string? MonthlyLoginCount { get; set; }
    [JsonPropertyName("ClientConnected")] public string? ClientConnected { get; set; }
    [JsonPropertyName("Points")] public string? Points { get; set; }
    [JsonPropertyName("PL")] public string? PL { get; set; }
    [JsonPropertyName("BL")] public string? BL { get; set; }
    [JsonPropertyName("HL")] public string? HL { get; set; }
    [JsonPropertyName("LAP")] public string? LAP { get; set; }
    [JsonPropertyName("CC")] public string? CC { get; set; }
}

public class DialerCounterDto
{
    [JsonPropertyName("Location")] public string? Location { get; set; }
    [JsonPropertyName("TL")] public string? TL { get; set; }
    [JsonPropertyName("Caller")] public string? Caller { get; set; }
    [JsonPropertyName("DND")] public string? DND { get; set; }
    [JsonPropertyName("Lead")] public string? Lead { get; set; }
    [JsonPropertyName("FollowUp")] public string? FollowUp { get; set; }
    [JsonPropertyName("NI")] public string? NI { get; set; }
    [JsonPropertyName("NotDoable")] public string? NotDoable { get; set; }
    [JsonPropertyName("WrongNo")] public string? WrongNo { get; set; }
    [JsonPropertyName("Blank")] public string? Blank { get; set; }
    [JsonPropertyName("Ringing")] public string? Ringing { get; set; }
    [JsonPropertyName("TotalCount")] public string? TotalCount { get; set; }
    [JsonPropertyName("Duration")] public string? Duration { get; set; }
    [JsonPropertyName("DisbursedAmt")] public string? DisbursedAmt { get; set; }
    [JsonPropertyName("TotalDisbursedAmt")] public string? TotalDisbursedAmt { get; set; }
    [JsonPropertyName("Rank")] public string? Rank { get; set; }
    [JsonPropertyName("DailyLogin")] public string? DailyLogin { get; set; }
    [JsonPropertyName("DailyLoginAmt")] public string? DailyLoginAmt { get; set; }
    [JsonPropertyName("SameDayFollowUp")] public string? SameDayFollowUp { get; set; }
    [JsonPropertyName("LastCalledDate")] public string? LastCalledDate { get; set; }
    [JsonPropertyName("LoginAmt")] public string? LoginAmt { get; set; }
    [JsonPropertyName("LoginCount")] public string? LoginCount { get; set; }
    [JsonPropertyName("DisbursedCount")] public string? DisbursedCount { get; set; }
}

public class LiveCountDto
{
    [JsonPropertyName("Lead")] public int Lead { get; set; }
    [JsonPropertyName("NotDoable")] public int NotDoable { get; set; }
    [JsonPropertyName("FollowUp")] public int FollowUp { get; set; }
    [JsonPropertyName("DND")] public int DND { get; set; }
    [JsonPropertyName("NI")] public int NI { get; set; }
    [JsonPropertyName("WrongNo")] public int WrongNo { get; set; }
    [JsonPropertyName("Blank")] public int Blank { get; set; }
    [JsonPropertyName("TotalCount")] public int TotalCount { get; set; }
    [JsonPropertyName("TotalClientConnected")] public int TotalClientConnected { get; set; }
    [JsonPropertyName("Duration")] public string? Duration { get; set; }
    [JsonPropertyName("TotalLogin")] public string? TotalLogin { get; set; }
    [JsonPropertyName("TotalLoginAmt")] public string? TotalLoginAmt { get; set; }
    [JsonPropertyName("DisbursedAmt")] public string? TotalDisbursedAmt { get; set; }
    [JsonPropertyName("DisburseLogin")] public string? TotalDisbursedCount { get; set; }
}

public class AttendanceDto
{
    [JsonPropertyName("intime")] public string? Intime { get; set; }
    [JsonPropertyName("outtime")] public string? Outtime { get; set; }
    [JsonPropertyName("entrydate")] public string? Entrydate { get; set; }
    [JsonPropertyName("IsHalfDay")] public string? IsHalfDay { get; set; }
    [JsonPropertyName("IsLate")] public string? IsLate { get; set; }
    [JsonPropertyName("Salary")] public string? Salary { get; set; }
    [JsonPropertyName("SalaryEarned")] public string? SalaryEarned { get; set; }
    [JsonPropertyName("TotalDays")] public string? TotalDays { get; set; }
    [JsonPropertyName("AbsentDays")] public string? AbsentDays { get; set; }
    [JsonPropertyName("HalfDay")] public string? HalfDay { get; set; }
    [JsonPropertyName("Late")] public string? Late { get; set; }
    [JsonPropertyName("workingdays")] public string? WorkingDays { get; set; }
}

public class PickupDto
{
    [JsonPropertyName("PickupKey")] public int PickupKey { get; set; }
    [JsonPropertyName("Name")] public string? Name { get; set; }
    [JsonPropertyName("Address")] public string? Address { get; set; }
    [JsonPropertyName("MobileNo")] public string? MobileNo { get; set; }
    [JsonPropertyName("PickupType")] public string? PickupType { get; set; }
    [JsonPropertyName("ProductDesc")] public string? ProductDesc { get; set; }
    [JsonPropertyName("Remarks")] public string? Remarks { get; set; }
    [JsonPropertyName("CallerUserName")] public string? CallerUserName { get; set; }
    [JsonPropertyName("PickupTimeFrom")] public string? PickupTimeFrom { get; set; }
    [JsonPropertyName("PickupTimeTo")] public string? PickupTimeTo { get; set; }
    [JsonPropertyName("BanksDesc")] public string? BanksDesc { get; set; }
    [JsonPropertyName("CallerLocation")] public string? CallerLocation { get; set; }
    [JsonPropertyName("TLName")] public string? TLName { get; set; }
    [JsonPropertyName("CallerTLNo")] public string? CallerTLNo { get; set; }
    [JsonPropertyName("PickupDate")] public string? PickupDate { get; set; }
    [JsonPropertyName("CallerRemarks")] public string? CallerRemarks { get; set; }
    [JsonPropertyName("Status")] public string? Status { get; set; }
    [JsonPropertyName("PickupName")] public string? PickupExecutive { get; set; }
    [JsonPropertyName("PickupContact")] public string? PickupExecutiveNo { get; set; }
    [JsonPropertyName("PushID")] public string? PushID { get; set; }
}

public class CallingDto
{
    [JsonPropertyName("CD_KEY")] public int CD_KEY { get; set; }
    [JsonPropertyName("CD_CLIENT_NAME")] public string? CD_CLIENT_NAME { get; set; }
    [JsonPropertyName("CD_CONTACT")] public string? CD_CONTACT { get; set; }
    [JsonPropertyName("CD_SALARY")] public string? CD_SALARY { get; set; }
    [JsonPropertyName("CD_COMPANY")] public string? CD_COMPANY { get; set; }
    [JsonPropertyName("CD_REMARKS")] public string? CD_REMARKS { get; set; }
    [JsonPropertyName("CD_CAMPAIGN_NAME")] public string? CD_CAMPAIGN_NAME { get; set; }
}

public class DialerRecordDto
{
    [JsonPropertyName("CD_KEY")] public int CD_KEY { get; set; }
    [JsonPropertyName("CD_CLIENT_NAME")] public string? CD_CLIENT_NAME { get; set; }
    [JsonPropertyName("CD_CONTACT")] public string? CD_CONTACT { get; set; }
    [JsonPropertyName("CD_SALARY")] public string? CD_SALARY { get; set; }
    [JsonPropertyName("CD_COMPANY")] public string? CD_COMPANY { get; set; }
    [JsonPropertyName("CD_STATUS")] public string? CD_STATUS { get; set; }
    [JsonPropertyName("CD_ASSIGNED_TO")] public string? CD_ASSIGNED_TO { get; set; }
    [JsonPropertyName("CD_ASSIGNED_TO_NAME")] public string? CD_ASSIGNED_TO_NAME { get; set; }
    [JsonPropertyName("CD_CALLBACK_DATE")] public string? CD_CALLBACK_DATE { get; set; }
    [JsonPropertyName("CD_CALLBACK_TIME")] public string? CD_CALLBACK_TIME { get; set; }
    [JsonPropertyName("CD_CALLER_REMARKS")] public string? CD_CALLER_REMARKS { get; set; }
    [JsonPropertyName("CD_CAMPAIGN_NAME")] public string? CD_CAMPAIGN_NAME { get; set; }
    [JsonPropertyName("CD_PRODUCT_Desc")] public string? CD_PRODUCT_Desc { get; set; }
    [JsonPropertyName("CD_CREATED_BY")] public string? CD_CREATED_BY { get; set; }
    [JsonPropertyName("CD_CREATED_ON")] public string? CD_CREATED_ON { get; set; }
    [JsonPropertyName("CD_MODIFIED_ON")] public string? CD_MODIFIED_ON { get; set; }
    [JsonPropertyName("CD_PRODUCT")] public string? CD_PRODUCT { get; set; }
    [JsonPropertyName("LeadKey")] public int LeadKey { get; set; }
    [JsonPropertyName("Name")] public string? Name { get; set; }
    [JsonPropertyName("MobileNo")] public string? MobileNo { get; set; }
    [JsonPropertyName("Status")] public string? Status { get; set; }
    [JsonPropertyName("Remarks")] public string? Remarks { get; set; }
    [JsonPropertyName("ProductDesc")] public string? ProductDesc { get; set; }
    [JsonPropertyName("AssignedToName")] public string? AssignedToName { get; set; }
    [JsonPropertyName("CallBackDate")] public string? CallBackDate { get; set; }
    [JsonPropertyName("CallBackTime")] public string? CallBackTime { get; set; }
    [JsonPropertyName("CallerRemarks")] public string? CallerRemarks { get; set; }
    [JsonPropertyName("CreatedByName")] public string? CreatedByName { get; set; }
    [JsonPropertyName("CreatedDate")] public string? CreatedDate { get; set; }
    [JsonPropertyName("ModifiedOn")] public string? ModifiedOn { get; set; }
}

public class CallLogListDto
{
    public string? date { get; set; }
    public string? number { get; set; }
    public string? type { get; set; }
    public string? duration { get; set; }
    public string? name { get; set; }
    public int userkey { get; set; }
}

public class ClientDocumentDto
{
    [JsonPropertyName("Id")] public int Id { get; set; }
    [JsonPropertyName("FileName")] public string? FileName { get; set; }
    [JsonPropertyName("FileType")] public string? FileType { get; set; }
    [JsonPropertyName("FileBase64String")] public string? FileBase64String { get; set; }
    [JsonPropertyName("Name")] public string? Name { get; set; }
    [JsonPropertyName("MobileNo")] public string? MobileNo { get; set; }
}
