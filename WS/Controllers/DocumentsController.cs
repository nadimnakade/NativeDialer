using Microsoft.AspNetCore.Mvc;
using WsClient.Data;

namespace WsClient.Controllers;

[ApiController]
[Route("Pickup")]
public class DocumentsController : ControllerBase
{
    private readonly DbConnectionFactory _db;
    public DocumentsController(DbConnectionFactory db) => _db = db;

    [AcceptVerbs("POST")]
    [Route("save-documents")]
    public async Task<IActionResult> SaveDocuments()
    {
        var httpRequest = HttpContext.Request;
        if (!httpRequest.HasFormContentType || httpRequest.Form.Files.Count == 0)
            return BadRequest(new { success = false, message = "No file uploaded" });

        var postedFile = httpRequest.Form.Files[0];
        string fileName = httpRequest.Form["FileName"].ToString();
        string caller = httpRequest.Form["Caller"].ToString();
        string mobileNo = httpRequest.Form["MobileNo"].ToString();
        string fileType = httpRequest.Form["FileType"].ToString();
        string name = httpRequest.Form["Name"].ToString();

        byte[] fileBytes;
        using (var ms = new MemoryStream())
        {
            await postedFile.CopyToAsync(ms);
            fileBytes = ms.ToArray();
        }

        await _db.ExecuteAsync("USP_SAVE_DOCUMENT_API", new { File = fileBytes, Caller = caller, FileName = fileName, MobileNo = mobileNo, FileType = fileType, Name = name });
        return Ok(new { success = true, message = "Success" });
    }
}
