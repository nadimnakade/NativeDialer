using Microsoft.AspNetCore.Mvc;

namespace DialerAPI.Controllers;

[ApiController]
[Route("api/Pdf")]
public class PdfController : ControllerBase
{
    [HttpGet]
    [Route("generate")]
    public IActionResult Generate([FromQuery] string userKey, [FromQuery] string type)
    {
        return Ok(new { success = true, message = "PDF generation endpoint" });
    }
}
