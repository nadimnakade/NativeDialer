using Microsoft.AspNetCore.Mvc;

namespace WsClient.Controllers;

[ApiController]
[Route("Pickup")]
public class EmailController : ControllerBase
{
    [AcceptVerbs("GET", "POST")]
    [Route("send-email")]
    public async Task<IActionResult> SendEmail([FromQuery] string body, [FromQuery] string user)
    {
        try
        {
            using var smtp = new System.Net.Mail.SmtpClient("smtp.gmail.com")
            {
                Port = 587,
                Credentials = new System.Net.NetworkCredential("oneup.helpdesk1up@gmail.com", "cip@12345"),
                EnableSsl = true,
            };
            var mail = new System.Net.Mail.MailMessage
            {
                From = new System.Net.Mail.MailAddress("oneup.helpdesk1up@gmail.com"),
                Subject = "Whistle Blower " + user,
                Body = body,
                IsBodyHtml = true,
            };
            foreach (var addr in "tejasdhrona@gmail.com;sarfaraz.ali1@gmail.com;bhushanajani@gmail.com;manishkukreja2901@gmail.com;kishan1upfinpro@gmail.com".Split(';'))
                mail.To.Add(addr);
            await smtp.SendMailAsync(mail);
            return Ok(new { success = true, message = "Email Sent" });
        }
        catch (Exception ex)
        {
            return StatusCode(500, new { success = false, message = ex.Message });
        }
    }
}
