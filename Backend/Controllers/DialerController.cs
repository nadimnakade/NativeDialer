using DialerAPI.Data;
using DialerAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace DialerAPI.Controllers;

[ApiController]
[Route("api/Pickup")]
public class DialerController : ControllerBase
{
    private readonly DbConnectionFactory _db;
    public DialerController(DbConnectionFactory db) => _db = db;

    [AcceptVerbs("GET", "POST")]
    [Route("get-dialer-data")]
    public async Task<IActionResult> GetDialerData([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<CallingDto>("USP_GET_DIALER_API", new { USER_KEY = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-dialer-not-updated")]
    public async Task<IActionResult> GetDialerNotUpdated([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync<DialerRecordDto>("USP_GET_DIALER_NOT_UPDATED_API", new { USER_KEY = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("update-dialing-status")]
    public async Task<IActionResult> UpdateDialingStatus([FromQuery] int key)
    {
        await _db.ExecuteAsync("USP_UPDATE_DIALER_STATUS_API", new { Key = key });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("update-dialer")]
    public async Task<IActionResult> UpdateDialer(
        [FromQuery] int key, [FromQuery] string? status, [FromQuery] string? remarks,
        [FromQuery] string? callBackTime, [FromQuery] string? callBackDate,
        [FromQuery] string? callDuration, [FromQuery] string? callDate,
        [FromQuery] int userKey, [FromQuery] int isHLLAPBL = 0,
        [FromQuery] int isCibilBad = 0, [FromQuery] int isRealEstate = 0)
    {
        await _db.ExecuteAsync("USP_CIP_UPDATE_DIALER_STATUS_API", new
        {
            Key = key, Status = status ?? "", Remarks = remarks,
            CallBackTime = callBackTime, CallBackDate = callBackDate,
            CallDuration = callDuration, CallDate = callDate,
            UserKey = userKey, IsHLLAPBL = isHLLAPBL,
            IsCibilBad = isCibilBad, IsRealEstate = isRealEstate
        });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-lead")]
    public async Task<IActionResult> GetLead([FromQuery] int userKey, [FromQuery] int pageIndex = 0)
    {
        var data = await _db.QueryAsync<DialerRecordDto>("USP_FETCH_LEAD_MASTER_API", new { USER_KEY = userKey, PageIndex = pageIndex });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("insert-lead")]
    public async Task<IActionResult> InsertLeadInfo(
        [FromQuery] string cD_CLIENT_NAME, [FromQuery] string cD_CONTACT,
        [FromQuery] string cD_SALARY, [FromQuery] string cD_COMPANY,
        [FromQuery] string cD_REMARKS, [FromQuery] string cD_STATUS,
        [FromQuery] string cD_CALLBACK_DATE, [FromQuery] string cD_CALLBACK_TIME,
        [FromQuery] string cD_CALLER_DURATION, [FromQuery] string cD_CALL_DATE_TIME,
        [FromQuery] int userKey, [FromQuery] int isHLLAPBL = 0,
        [FromQuery] int isCibilBad = 0, [FromQuery] int isRealEstate = 0)
    {
        await _db.ExecuteAsync("USP_CIP_INSERT_LEAD_MANUAL_API", new
        {
            CD_CLIENT_NAME = cD_CLIENT_NAME, CD_CONTACT = cD_CONTACT,
            CD_SALARY = cD_SALARY, CD_COMPANY = cD_COMPANY,
            CD_REMARKS = cD_REMARKS, CD_STATUS = cD_STATUS,
            CD_CALLBACK_DATE = cD_CALLBACK_DATE, CD_CALLBACK_TIME = cD_CALLBACK_TIME,
            CD_CALLER_DURATION = cD_CALLER_DURATION, CD_CALL_DATE_TIME = cD_CALL_DATE_TIME,
            UserKey = userKey,
            CD_IS_HLLAPBL = isHLLAPBL, CD_IS_CIBIL_BAD = isCibilBad,
            CD_IS_REALESTATE = isRealEstate
        });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("add-lead")]
    public async Task<IActionResult> AddLead(
        [FromQuery] string name, [FromQuery] string mobileNo,
        [FromQuery] string product, [FromQuery] string? callerId,
        [FromQuery] string? remarks, [FromQuery] int userKey)
    {
        await _db.ExecuteAsync("USP_CIP_ADD_LEAD_MASTER_API", new
        {
            Name = name, Contact = mobileNo, Product = product,
            CallerID = callerId ?? "", Remarks = remarks ?? "", UserKey = userKey
        });
        return Ok(new { success = true, message = "Successful" });
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-lead-followup")]
    public async Task<IActionResult> GetLeadnFollowupData([FromQuery] int userKey, [FromQuery] string type)
    {
        var data = await _db.QueryAsync<DialerRecordDto>("USP_GET_LEAD_N_FOLLOWUP_DATA_API", new { USER_KEY = userKey, TYPE = type });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("get-caller-data")]
    public async Task<IActionResult> GetCallerData([FromQuery] int userKey, [FromQuery] string type, [FromQuery] int pageIndex)
    {
        var data = await _db.QueryAsync<DialerRecordDto>("USP_GET_LEAD_N_FOLLOWUP_DATA_API_TEST", new { USER_KEY = userKey, TYPE = type, PAGEINDEX = pageIndex });
        return Ok(data);
    }
}
