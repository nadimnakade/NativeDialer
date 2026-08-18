using Dapper;
using DialerAPI.Data;
using DialerAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace DialerAPI.Controllers;

[ApiController]
[Route("api/Pickup")]
public class PickupController : ControllerBase
{
    private readonly DbConnectionFactory _db;
    public PickupController(DbConnectionFactory db) => _db = db;

    [AcceptVerbs("GET", "POST")]
    [Route("dashboard-counter")]
    public async Task<IActionResult> DashboardCounter([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<DashboardCounterDto>("USP_DASHBOARD_COUNT_API", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("dialer-live")]
    public async Task<IActionResult> DialerLive([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<DialerCounterDto>("USP_DIALER_LIVE_COUNT_REPORT", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("dialer-live-indv")]
    public async Task<IActionResult> DialerLiveIndv([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<DialerCounterDto>("USP_DIALER_LIVE_COUNT_REPORT_API", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("live-count")]
    public async Task<IActionResult> FetchLiveCount([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<LiveCountDto>("USP_DIALER_LIVE_COUNT_API", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("team-call-log")]
    public async Task<IActionResult> GetTeamCallLog([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<DialerCounterDto>("USP_DIALER_LIVE_COUNT_REPORT", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("attendance")]
    public async Task<IActionResult> GetAttendance([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<AttendanceDto>("USP_GET_ATTENDANCE_API", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("incentive")]
    public async Task<IActionResult> GetIncentive([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_GET_INCENTIVE_API", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("applications")]
    public async Task<IActionResult> GetApplications([FromQuery] int userKey, [FromQuery] string? searchText)
    {
        using var conn = _db.Create();
        var data = await conn.QueryAsync("USP_SEARCH_APPLICATION_API", new { Caller = userKey, SearchText = searchText ?? "" }, commandType: System.Data.CommandType.StoredProcedure);
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-pickup-data")]
    public async Task<IActionResult> GetPickupData([FromQuery] string mobileNo)
    {
        var data = await _db.QueryAsync<PickupDto>("USP_FETCH_PICKUP_API", new { MobileNo = mobileNo });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-pickup-entry-data")]
    public async Task<IActionResult> GetPickupEntryData([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<PickupDto>("USP_FETCH_PICKUP_DATA_ENTRY_API", new { USER_KEY = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("ranking")]
    public async Task<IActionResult> GetRanking([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_CALLERS_RANKING", new { USER_KEY = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("performance")]
    public async Task<IActionResult> GetPerformance([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_GET_MONTHLY_MIS_REPORT_DISBURSED_INDV", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("lead-count")]
    public async Task<IActionResult> GetLeadCount([FromQuery] int userKey, [FromQuery] int type)
    {
        var data = await _db.QueryAsync("USP_GET_LEAD_COUNT_API", new { USER_KEY = userKey, TYPE = type });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-daily-login-list")]
    public async Task<IActionResult> GetDailyLoginList([FromQuery] int userKey, [FromQuery] string? searchText)
    {
        using var conn = _db.Create();
        var data = await conn.QueryAsync("USP_GET_DAILY_LOGIN_LIST_API", new { Caller = userKey, SearchText = searchText ?? "" }, commandType: System.Data.CommandType.StoredProcedure);
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("product")]
    public async Task<IActionResult> GetProduct([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_GET_PRODUCT", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("caller-clients")]
    public async Task<IActionResult> GetCallerClients([FromQuery] int userKey, [FromQuery] string mobileNo)
    {
        var data = await _db.QueryAsync<ClientDocumentDto>("USP_GET_CALLER_CLIENTS_API", new { UserKey = userKey, MobileNo = mobileNo });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("client-documents")]
    public async Task<IActionResult> GetClientDocuments([FromQuery] int userKey, [FromQuery] string mobileNo)
    {
        var data = await _db.QueryAsync<ClientDocumentDto>("USP_GET_CLIENT_DOCUMENTS_API", new { UserKey = userKey, MobileNo = mobileNo });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("is-attendance-marked")]
    public async Task<IActionResult> IsAttendanceMarked([FromQuery] int userKey, [FromQuery] int type)
    {
        var data = await _db.QueryAsync("USP_IF_ATTENDANCE_MARKED_API", new { UserKey = userKey, Type = type });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("disbursal-detail")]
    public async Task<IActionResult> GetDisbursalDtl([FromQuery] int userKey, [FromQuery] string loanId)
    {
        var data = await _db.QueryAsync("USP_GET_DISBURSAL_TWISE_API", new { UserKey = userKey, LoanId = loanId });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("update-status")]
    public async Task<IActionResult> UpdateStatus(
        [FromQuery] int key, [FromQuery] string? callerRemarks,
        [FromQuery] string? status, [FromQuery] string? callBackDate,
        [FromQuery] string? callBackTime, [FromQuery] string? lat,
        [FromQuery] string? lng, [FromQuery] string? image)
    {
        await _db.ExecuteAsync("USP_CIP_UPDATE_PICKUP_REMARKS_API", new
        {
            Key = key, Status = status ?? "", CallerRemarks = callerRemarks ?? "",
            CallBackDate = callBackDate, CallBackTime = callBackTime,
            LAT = lat ?? "", LONG = lng ?? "", IMAGE = image ?? ""
        });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("insert-pickup")]
    public async Task<IActionResult> InsertPickup(
        [FromQuery] int pickupKey, [FromQuery] string? name,
        [FromQuery] string? mobileNo, [FromQuery] string? banks,
        [FromQuery] string? pickupType, [FromQuery] string? product,
        [FromQuery] string? callerUserID, [FromQuery] string? pickupFrom,
        [FromQuery] string? pickupTo, [FromQuery] string? pickupDate,
        [FromQuery] string? pickupRegion, [FromQuery] string? address,
        [FromQuery] string? remarks, [FromQuery] int userKey)
    {
        await _db.ExecuteAsync("USP_MANAGE_Pickup_MASTER_API", new
        {
            LM_KEY = pickupKey, LM_NAME = name ?? "",
            LM_PICKUP_DATE = pickupDate, LM_PICKUP_TYPE = pickupType,
            LM_CONTACT_NO = mobileNo, LM_ADDRESS = address ?? "",
            LM_PRODUCT = product, LM_CALLER_USERID = callerUserID,
            LM_REMARKS = remarks ?? "", LM_PICKUP_TIME_FROM = pickupFrom,
            LM_PICKUP_TIME_TO = pickupTo, LM_BRANCH_KEY = banks,
            LM_LOGIN_REGION = pickupRegion, LM_CREATED_BY = userKey
        });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("manage-lead")]
    public async Task<IActionResult> ManageLead(
        [FromQuery] int key, [FromQuery] string? name,
        [FromQuery] string? emailId, [FromQuery] string? mobileNo,
        [FromQuery] string? address, [FromQuery] int productKey,
        [FromQuery] int userKey)
    {
        await _db.ExecuteAsync("USP_MANAGE_LEAD_MASTER", new
        {
            LM_KEY = key, LM_NAME = name ?? "",
            LM_EMAIL_ID = emailId ?? "", LM_CONTACT_NO = mobileNo ?? "",
            LM_ADDRESS = address ?? "", LM_PRODUCT = productKey,
            LM_CREATED_BY = userKey
        });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-executive")]
    public async Task<IActionResult> GetExecutive([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_CIP_GET_pickup_exec_api", new { USER_KEY = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-push-id")]
    public async Task<IActionResult> GetPushID([FromQuery] int userKey)
    {
        var data = await _db.QuerySingleAsync<string>("USP_FETCH_PUSH_ID_API", new { UserKey = userKey });
        return Ok(new { pushId = data ?? "" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("validate-login-mobile")]
    public async Task<IActionResult> ValidateLoginMobile(
        [FromQuery] string mobileNo, [FromQuery] string uDID, [FromQuery] string? type)
    {
        var result = await _db.QuerySingleAsync<string>("USP_VALIDATE_LOGIN_API",
            new { MobileNo = mobileNo, UDID = uDID ?? "", Type = type ?? "" });
        return Ok(new { result });
    }
}
