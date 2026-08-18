using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace WsClient.Controllers;

[ApiController]
[Route("ws")]
public class AudioWebSocketController : ControllerBase
{
    private readonly RtpAudioService _rtpService;
    private readonly ILogger<AudioWebSocketController> _logger;

    public AudioWebSocketController(RtpAudioService rtpService, ILogger<AudioWebSocketController> logger)
    {
        _rtpService = rtpService;
        _logger = logger;
    }

    [HttpGet("audio")]
    public async Task GetAudioWebSocket()
    {
        if (!HttpContext.WebSockets.IsWebSocketRequest)
        {
            HttpContext.Response.StatusCode = 400;
            return;
        }

        var webSocket = await HttpContext.WebSockets.AcceptWebSocketAsync("audio");
        var sessionKey = Guid.NewGuid().ToString("N")[..12];

        _logger.LogInformation("Audio WebSocket connected: {Key}", sessionKey);

        var session = new AudioSession(webSocket, _rtpService, _logger);
        _rtpService.RegisterSession(sessionKey, session);

        try
        {
            await session.RunAsync(HttpContext.RequestAborted);
        }
        finally
        {
            _rtpService.UnregisterSession(sessionKey);
            _logger.LogInformation("Audio WebSocket disconnected: {Key}", sessionKey);
        }
    }
}
