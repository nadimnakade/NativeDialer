using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using WsClient.Data;
using WsClient.Models;

namespace WsClient.Controllers;

[ApiController]
[Route("Pickup")]
public class CallLogController : ControllerBase
{
    private readonly DialerHelper _dialer;
    private readonly IConfiguration _config;

    public CallLogController(DialerHelper dialer, IConfiguration config)
    {
        _dialer = dialer;
        _config = config;
    }

    [AcceptVerbs("GET", "POST")]
    [Route("save-call-logs")]
    public IActionResult FnSaveCallLogs([FromBody] List<CallLogListDto> lstCallLog)
    {
        _dialer.FnSaveCallLogs(lstCallLog);
        return Ok("Ok");
    }

    [AcceptVerbs("GET", "POST")]
    [Route("save-call-logs-all")]
    public IActionResult FnSaveCallLogsAll([FromBody] List<CallLogListDto> callLog)
    {
        _dialer.FnSaveCallLogsAll(callLog);
        return Ok("Ok");
    }

    [AcceptVerbs("GET", "POST")]
    [Route("save-call-logs-indv")]
    public async Task<IActionResult> FnSaveCallLogsIndv(
        [FromQuery] string date, [FromQuery] string type,
        [FromQuery] string duration, [FromQuery] string name,
        [FromQuery] string number, [FromQuery] int userkey)
    {
        var connStr = _config.GetConnectionString("DefaultConnection");
        using var conn = new SqlConnection(connStr);
        await conn.OpenAsync();
        using var cmd = new SqlCommand("USP_CALL_LOG", conn);
        cmd.CommandType = System.Data.CommandType.StoredProcedure;
        cmd.Parameters.AddWithValue("@name", name ?? "");
        cmd.Parameters.AddWithValue("@date", date ?? "");
        cmd.Parameters.AddWithValue("@duration", duration ?? "");
        cmd.Parameters.AddWithValue("@type", type ?? "");
        cmd.Parameters.AddWithValue("@number", number ?? "");
        cmd.Parameters.AddWithValue("@userkey", userkey);
        await cmd.ExecuteNonQueryAsync();
        return Ok("Ok");
    }

    [AcceptVerbs("GET", "POST")]
    [Route("save-recording")]
    public async Task<IActionResult> SaveRecording()
    {
        var httpRequest = HttpContext.Request;
        if (!httpRequest.HasFormContentType || httpRequest.Form.Files.Count == 0)
            return BadRequest(new { success = false, message = "No file uploaded" });

        var connStr = _config.GetConnectionString("DefaultConnection");
        foreach (var postedFile in httpRequest.Form.Files)
        {
            string baseFolder = @"H:\IIS\Recordings";
            DateTime d = DateTime.Now;
            string dateFolder = d.ToString("ddMMyyyy");
            string caller = httpRequest.Form["Caller"].ToString();
            string targetFolder = Path.Combine(baseFolder, caller, dateFolder);
            Directory.CreateDirectory(targetFolder);

            string fileName = httpRequest.Form["FileName"] + ".mp3";
            string targetPath = Path.Combine(targetFolder, fileName);
            using (var stream = new FileStream(targetPath, FileMode.Create))
                await postedFile.CopyToAsync(stream);

            int key = int.Parse(httpRequest.Form["CD_KEY"].ToString());
            string contact = httpRequest.Form["CD_CONTACT"].ToString();
            using var conn = new SqlConnection(connStr);
            await conn.OpenAsync();
            using var cmd = new SqlCommand("USP_SAVE_RECORDING_DETAIL_API", conn);
            cmd.CommandType = System.Data.CommandType.StoredProcedure;
            cmd.Parameters.AddWithValue("@Key", key);
            cmd.Parameters.AddWithValue("@Caller", caller);
            cmd.Parameters.AddWithValue("@FileName", httpRequest.Form["FileName"].ToString());
            cmd.Parameters.AddWithValue("@DateFolder", dateFolder);
            cmd.Parameters.AddWithValue("@Contact", contact);
            await cmd.ExecuteNonQueryAsync();
        }
        return Ok(new { success = true, message = "Success" });
    }
}
