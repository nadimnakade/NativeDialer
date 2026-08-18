using Microsoft.AspNetCore.Mvc;

namespace WsClient.Controllers;

[ApiController]
[Route("Pdf")]
public class PdfController : ControllerBase
{
    [HttpGet]
    [Route("generate")]
    public IActionResult Generate([FromQuery] string userKey, [FromQuery] string type)
    {
        return Ok(new { success = true, message = "PDF generation endpoint" });
    }
}
