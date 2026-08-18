using DialerAPI.Data;
using Microsoft.AspNetCore.Mvc;

namespace DialerAPI.Controllers;

[ApiController]
[Route("api/Pickup")]
public class DailyLoginController : ControllerBase
{
    private readonly DbConnectionFactory _db;
    public DailyLoginController(DbConnectionFactory db) => _db = db;

    [AcceptVerbs("GET", "POST")]
    [Route("insert-daily-login")]
    public async Task<IActionResult> InsertDailyLogins(
        [FromQuery] int product, [FromQuery] string banks,
        [FromQuery] string losNo, [FromQuery] string appNo,
        [FromQuery] int region, [FromQuery] string clientName,
        [FromQuery] string contact, [FromQuery] string loginAmt,
        [FromQuery] string? remarks, [FromQuery] int userKey)
    {
        decimal? login = decimal.TryParse(loginAmt, out var amt) ? amt : 0;
        await _db.ExecuteAsync("USP_INSERT_DAILY_LOGIN_API", new
        {
            Product = product, Banks = banks, LOSNo = losNo, AppNo = appNo,
            Region = region, ClientName = clientName, Contact = contact,
            LoginAmt = login, Remarks = remarks ?? "", UserKey = userKey
        });
        return Ok(new { success = true, message = "Data Inserted Successfully" });
    }
}
