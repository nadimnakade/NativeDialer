using Microsoft.AspNetCore.Mvc;
using WsClient.Data;
using WsClient.Models;

namespace WsClient.Controllers;

[ApiController]
[Route("UpdateStatus")]
public class UpdateStatusController : ControllerBase
{
    private readonly DbConnectionFactory _db;

    public UpdateStatusController(DbConnectionFactory db) => _db = db;

    [AcceptVerbs("GET", "POST")]
    [Route("ValidateLogin")]
    public async Task<IActionResult> ValidateLogin([FromQuery] string userName, [FromQuery] string password, [FromQuery] string uUID, [FromQuery] string? type)
    {
        var data = await _db.QueryAsync<EmployeeDto>("USP_CIP_GET_EMPLOYEE_LOGIN_DETAIL_API",
            new { UserName = userName, Password = password, UUID = uUID ?? "", Type = type ?? "CRM" });
        var list = data.ToList();
        if (!list.Any()) return Unauthorized(new { success = false, message = "Invalid credentials" });
        return Ok(list);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("GetCaller")]
    public async Task<IActionResult> GetCaller([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_CIP_GET_EMPLOYEE_LIST", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("AssignPickup")]
    public async Task<IActionResult> AssignPickup([FromQuery] int pickupKey, [FromQuery] int assignedTo, [FromQuery] int userKey)
    {
        await _db.ExecuteAsync("USP_CIP_UPDATE_Pickup_ASSIGNMENT",
            new { Key = pickupKey, AssignedTo = assignedTo, AssignedBy = userKey });
        return Ok("Successful");
    }

    [AcceptVerbs("GET", "POST")]
    [Route("GetPickup")]
    public async Task<IActionResult> GetPickup([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_CIP_GET_PICKUP_EMPLOYEE_LIST", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("GetLead")]
    public async Task<IActionResult> GetLead([FromQuery] int userKey, [FromQuery] string? searchText, [FromQuery] int pageSize = 50, [FromQuery] int pageIndex = 0)
    {
        var data = await _db.QueryAsync("USP_FETCH_LEAD_MASTER_RECORD_API",
            new { UserKey = userKey, SearchText = searchText ?? "", PageSize = pageSize, PageIndex = pageIndex });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("GetRanking")]
    public async Task<IActionResult> GetRanking([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_CALLERS_RANKING", new { USER_KEY = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("IsAttendanceMarked")]
    public async Task<IActionResult> IsAttendanceMarked([FromQuery] int userKey, [FromQuery] int type)
    {
        var data = await _db.QueryAsync("USP_IF_ATTENDANCE_MARKED_API", new { UserKey = userKey, Type = type });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("GetClientsBirthday")]
    public async Task<IActionResult> GetClientsBirthday([FromQuery] int userKey)
    {
        var data = await _db.QueryAsync("USP_CLIENT_BIRTHDAY_API", new { UserKey = userKey });
        return Ok(data);
    }

    [AcceptVerbs("GET", "POST")]
    [Route("FnSaveCallLogsAll")]
    public IActionResult FnSaveCallLogsAll([FromBody] List<CallLogListDto> callLog)
    {
        var dialer = HttpContext.RequestServices.GetRequiredService<DialerHelper>();
        dialer.FnSaveCallLogsAll(callLog);
        return Ok("Ok");
    }

    [AcceptVerbs("GET", "POST")]
    [Route("FnIndvCallLog")]
    public IActionResult FnIndvCallLog([FromBody] List<CallLogListDto> callLog)
    {
        var dialer = HttpContext.RequestServices.GetRequiredService<DialerHelper>();
        dialer.FnIndvCallLog(callLog);
        return Ok("Ok");
    }

    [AcceptVerbs("GET", "POST")]
    [Route("GetDisbursalDtl")]
    public async Task<IActionResult> GetDisbursalDtl([FromQuery] int userKey, [FromQuery] string loanId)
    {
        var data = await _db.QueryAsync("USP_GET_DISBURSAL_TWISE_API", new { UserKey = userKey, LoanId = loanId });
        return Ok(data);
    }
}

public class EmployeeDto
{
    public int Key { get; set; }
    public int EmployeeNo { get; set; }
    public string? EmployeeName { get; set; }
    public string? UserName { get; set; }
    public string? Password { get; set; }
    public int DesignationID { get; set; }
    public string? DesignationDesc { get; set; }
    public long RoleID { get; set; }
    public string? RoleDesc { get; set; }
    public decimal Salary { get; set; }
    public long BossId { get; set; }
    public string? ReportsToName { get; set; }
    public string? EmailId { get; set; }
    public long ProgrammeManagerID { get; set; }
    public string? ProgrammeManagerName { get; set; }
    public long ReportingManagerID { get; set; }
    public string? ReportingManagerName { get; set; }
    public string? BankAccountNo { get; set; }
    public string? LocationDesc { get; set; }
    public string? DateOfJoining { get; set; }
    public string? DateOfBirth { get; set; }
    public string? Address { get; set; }
    public long OfficeContactNo { get; set; }
    public long MobileNo1 { get; set; }
    public long MobileNo2 { get; set; }
    public string? AadharNo { get; set; }
    public string? PanNo { get; set; }
}
