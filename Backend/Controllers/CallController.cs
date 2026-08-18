using DialerAPI.Models;
using DialerAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace DialerAPI.Controllers;

[ApiController]
[Route("api/Call")]
public class CallController : ControllerBase
{
    private readonly SipWebSocketService _sipService;
    private readonly CallSessionManager _sessionManager;
    private readonly ILogger<CallController> _logger;

    public CallController(
        SipWebSocketService sipService,
        CallSessionManager sessionManager,
        ILogger<CallController> logger)
    {
        _sipService = sipService;
        _sessionManager = sessionManager;
        _logger = logger;
    }

    [HttpPost("Place")]
    public async Task<IActionResult> PlaceCall([FromBody] PlaceCallRequest request)
    {
        
        if (string.IsNullOrWhiteSpace(request.PhoneNumber))
            return BadRequest(new PlaceCallResponse { Success = false, Message = "phone_number is required" });

        if (string.IsNullOrWhiteSpace(request.UserKey))
            return BadRequest(new PlaceCallResponse { Success = false, Message = "user_key is required" });

        if (!_sipService.IsRegistered)
        {
            _logger.LogWarning("Cannot place call - SIP not registered");
            return StatusCode(503, new PlaceCallResponse
            {
                Success = false,
                Message = "SIP service not registered. Try again shortly."
            });
        }

        var session = _sessionManager.CreateSession(request.UserKey, request.PhoneNumber);

        var sent = await _sipService.SendInvite(request.PhoneNumber, session.CallId, HttpContext.RequestAborted);

        if (!sent)
        {
            _sessionManager.EndSession(session.CallId);
            return StatusCode(502, new PlaceCallResponse
            {
                Success = false,
                Message = "Failed to send INVITE to SIP server"
            });
        }

        _logger.LogInformation("Call placed: id={CallId}, callee={Callee}, user={User}",
            session.CallId, request.PhoneNumber, request.UserKey);

        return Ok(new PlaceCallResponse
        {
            Success = true,
            CallId = session.CallId,
            Message = "Call initiated"
        });
    }

    [HttpPost("Hangup")]
    public async Task<IActionResult> HangupCall([FromBody] HangupCallRequest request)
    {
        if (string.IsNullOrWhiteSpace(request.CallId))
            return BadRequest(new { success = false, message = "call_id is required" });

        var session = _sessionManager.GetSession(request.CallId);
        if (session == null)
            return NotFound(new { success = false, message = "Call not found" });

        if (session.State is CallSessionState.Ended or CallSessionState.Failed)
            return Ok(new { success = true, message = "Call already ended" });

        await _sipService.SendBye(request.CallId, HttpContext.RequestAborted);
        _sessionManager.EndSession(request.CallId);

        _logger.LogInformation("Call hung up: id={CallId}", request.CallId);

        return Ok(new { success = true, message = "Hangup sent" });
    }

    [HttpGet("Status")]
    public IActionResult GetCallStatus([FromQuery] string callId)
    {
        if (string.IsNullOrWhiteSpace(callId))
            return BadRequest(new { success = false, message = "call_id is required" });

        var session = _sessionManager.GetSession(callId);
        if (session == null)
            return NotFound(new { success = false, message = "Call not found" });

        var duration = session.ConnectedAt.HasValue
            ? (int)((session.EndedAt ?? DateTime.UtcNow) - session.ConnectedAt.Value).TotalSeconds
            : 0;

        return Ok(new CallStatusResponse
        {
            CallId = session.CallId,
            State = session.State.ToString().ToLower(),
            DurationSeconds = duration,
            Callee = session.Callee,
            UserKey = session.UserKey
        });
    }

    [HttpGet("active")]
    public IActionResult GetActiveCalls()
    {
        var active = _sessionManager.GetActiveSessions().Select(s => new CallStatusResponse
        {
            CallId = s.CallId,
            State = s.State.ToString().ToLower(),
            DurationSeconds = s.ConnectedAt.HasValue
                ? (int)(DateTime.UtcNow - s.ConnectedAt.Value).TotalSeconds : 0,
            Callee = s.Callee,
            UserKey = s.UserKey
        });

        return Ok(active);
    }

    [HttpGet("health")]
    public IActionResult ServiceStatus()
    {
        return Ok(new
        {
            sip_registered = _sipService.IsRegistered,
            active_calls = _sessionManager.GetActiveSessions().Count(),
            timestamp = DateTime.UtcNow
        });
    }
}
