using System.Text.Json;
using Microsoft.AspNetCore.Http.Features;
using Microsoft.AspNetCore.Mvc;
using WsClient.Models;
using WsClient.Services;

namespace WsClient.Controllers;

[ApiController]
[Route("Call")]
public class CallController : ControllerBase
{
    private readonly SipWorkerService _sipService;
    private readonly CallSessionManager _sessionManager;
    private readonly ISseBroadcaster _sse;
    private readonly ILogger<CallController> _logger;

    public CallController(
        SipWorkerService sipService,
        CallSessionManager sessionManager,
        ISseBroadcaster sse,
        ILogger<CallController> logger)
    {
        _sipService = sipService;
        _sessionManager = sessionManager;
        _sse = sse;
        _logger = logger;
    }

    [HttpGet("events")]
    public async Task GetEvents([FromQuery] string userKey, CancellationToken ct)
    {
        Response.Headers.Append("Content-Type", "text/event-stream");
        Response.Headers.Append("Cache-Control", "no-cache");
        Response.Headers.Append("Connection", "keep-alive");
        Response.Headers.Append("X-Accel-Buffering", "no");

        var responseBodyFeature = HttpContext.Features.Get<IHttpResponseBodyFeature>();
        responseBodyFeature?.DisableBuffering();

        await HttpContext.Response.StartAsync(ct);

        var channel = _sse.Subscribe();
        _logger.LogInformation("SSE client subscribed for user={UserKey}", userKey);

        var body = Response.Body;
        var utf8 = System.Text.Encoding.UTF8;

        async Task WriteSse(string text, CancellationToken cancellation)
        {
            var bytes = utf8.GetBytes(text);
            await body.WriteAsync(bytes, cancellation);
            await body.FlushAsync(cancellation);
        }

        try
        {
            await WriteSse(": connected\r\n\r\n", ct);

            using var keepalive = new Timer(async _ =>
            {
                try { await WriteSse(": keepalive\r\n\r\n", CancellationToken.None); }
                catch { }
            }, null, TimeSpan.FromSeconds(10), TimeSpan.FromSeconds(10));

            var eventCount = 0;
            while (!ct.IsCancellationRequested)
            {
                if (!await channel.Reader.WaitToReadAsync(ct)) break;

                while (channel.Reader.TryRead(out var sseEvent))
                {
                    eventCount++;
                    var json = JsonSerializer.Serialize(sseEvent);
                    await WriteSse($"event: {sseEvent.Event}\r\n", ct);
                    await WriteSse($"data: {json}\r\n\r\n", ct);
                }
            }
        }
        catch (OperationCanceledException) { }
        catch (IOException) { _logger.LogInformation("SSE client disconnected (IO)"); }
        finally
        {
            _sse.Unsubscribe(channel);
        }
    }

    [HttpGet("Status/{callId}")]
    public IActionResult GetCallStatusPath(string callId)
    {
        return GetCallStatus(callId);
    }

    [HttpPost("Place")]
    public async Task<IActionResult> PlaceCall([FromBody] PlaceCallRequest request)
    {
        if (string.IsNullOrWhiteSpace(request.PhoneNumber))
            return BadRequest(new PlaceCallResponse { Success = false, Message = "phone_number is required" });

        if (string.IsNullOrWhiteSpace(request.UserKey))
            return BadRequest(new PlaceCallResponse { Success = false, Message = "user_key is required" });

        if (!_sipService.Client.IsRegistered)
        {
            _logger.LogWarning("Cannot place call - SIP not registered");
            return StatusCode(503, new PlaceCallResponse
            {
                Success = false,
                Message = "SIP service not registered. Try again shortly."
            });
        }

        var session = _sessionManager.CreateSession(request.UserKey, request.PhoneNumber);

        try
        {
            await _sipService.Client.SendInviteAsync(request.PhoneNumber);
            var sipCallId = _sipService.Client.ActiveCallId ?? "";
            _sessionManager.MapSipCallId(session.CallId, sipCallId);
            _logger.LogInformation("Mapped session {CallId} to SIP Call-ID {SipCallId}", session.CallId, sipCallId);
        }
        catch (Exception ex)
        {
            _sessionManager.EndSession(session.CallId);
            _logger.LogError(ex, "Failed to send INVITE for call {CallId}", session.CallId);
            return StatusCode(502, new PlaceCallResponse
            {
                Success = false,
                Message = $"Failed to send INVITE: {ex.Message}"
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

        try
        {
            await _sipService.Client.SendByeAsync(session.Callee, session.SipCallId);
        }
        catch (Exception ex)
        {
            _logger.LogWarning(ex, "Error sending BYE for call {CallId}", request.CallId);
        }

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
            sip_registered = _sipService.Client.IsRegistered,
            active_calls = _sessionManager.GetActiveSessions().Count(),
            timestamp = DateTime.UtcNow
        });
    }
}
