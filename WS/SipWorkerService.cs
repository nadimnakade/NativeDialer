using Microsoft.Extensions.Options;
using WsClient.Models;
using WsClient.Services;

namespace WsClient;

public class SipWorkerService : BackgroundService
{
    private readonly SipWebSocketClient _client;
    private readonly ILogger<SipWorkerService> _logger;
    private readonly ILogger<SipWebSocketClient> _clientLogger;
    private readonly SipConfig _config;
    private readonly LogBuffer _log;
    private readonly ISseBroadcaster _sse;
    private readonly CallSessionManager _sessions;

    public SipWorkerService(IOptions<SipConfig> config, ILogger<SipWorkerService> logger,
        ILoggerFactory loggerFactory, LogBuffer log, ISseBroadcaster sse, CallSessionManager sessions)
    {
        _config = config.Value;
        _logger = logger;
        _clientLogger = loggerFactory.CreateLogger<SipWebSocketClient>();
        _log = log;
        _sse = sse;
        _sessions = sessions;

        _client = new SipWebSocketClient(
            _config.Server, _config.Port, _config.Username, _config.Password, _config.Realm,
            _config.UserAgent, _config.UseTls, _clientLogger, _log);

        _client.OnRegistered += () => { _log.Write("REGISTERED with SIP server"); _logger.LogInformation("Registered with SIP server"); };
        _client.OnDisconnected += () => { LastError = "Server closed connection"; _log.Write("DISCONNECTED from SIP server"); _logger.LogWarning("Disconnected from SIP server"); };
        _client.OnError += msg => { _log.Write($"ERROR: {msg}"); _logger.LogError("SIP error: {Message}", msg); };
        _client.OnCallRinging += sipCallId =>
        {
            _logger.LogInformation("SIP OnCallRinging: sipCallId={SipCallId}", sipCallId);
            var session = _sessions.GetSessionBySipCallId(sipCallId);
            if (session != null)
                _sessions.UpdateState(session.CallId, CallSessionState.Ringing);
            _sse.Broadcast(new SseEvent { Event = "ringing", CallId = session?.CallId ?? "", State = "ringing", SipCallId = sipCallId });
        };
        _client.OnCallAnswered += sipCallId =>
        {
            _logger.LogInformation("SIP OnCallAnswered: sipCallId={SipCallId}", sipCallId);
            var session = _sessions.GetSessionBySipCallId(sipCallId);
            if (session != null)
                _sessions.UpdateState(session.CallId, CallSessionState.Connected);
            _sse.Broadcast(new SseEvent { Event = "answered", CallId = session?.CallId ?? "", State = "connected", SipCallId = sipCallId });
        };
        _client.OnMediaInfoReady += (remoteIp, remotePort, codec) =>
        {
            _logger.LogInformation("SIP OnMediaInfoReady: {Ip}:{Port} codec={Codec}", remoteIp, remotePort, codec);
            _sse.Broadcast(new SseEvent
            {
                Event = "media_info",
                State = "media_info",
                RemoteMediaIp = remoteIp,
                RemoteMediaPort = remotePort,
                Codec = codec
            });
        };
        _client.OnCallFailed += (sipCallId, reason) =>
        {
            _logger.LogInformation("SIP OnCallFailed: sipCallId={SipCallId} reason={Reason}", sipCallId, reason);
            var session = _sessions.GetSessionBySipCallId(sipCallId);
            if (session != null)
            {
                _sessions.UpdateState(session.CallId, CallSessionState.Failed);
                session.ErrorMessage = reason;
            }
            _sse.Broadcast(new SseEvent { Event = "failed", CallId = session?.CallId ?? "", State = "failed", Message = reason, SipCallId = sipCallId });
        };
        _client.OnCallEnded += sipCallId =>
        {
            _logger.LogInformation("SIP OnCallEnded: sipCallId={SipCallId}", sipCallId);
            var session = _sessions.GetSessionBySipCallId(sipCallId);
            if (session != null)
                _sessions.EndSession(session.CallId);
            _sse.Broadcast(new SseEvent { Event = "ended", CallId = session?.CallId ?? "", State = "ended", SipCallId = sipCallId });
        };
    }

    public SipWebSocketClient Client => _client;
    public string LastError { get; private set; } = "";

    public SipConfig GetConfig() => _config;

    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        _log.Write($"Worker starting — connecting to {_config.Server}:{_config.Port}");
        _logger.LogInformation("SIP WebSocket worker starting...");

        while (!stoppingToken.IsCancellationRequested)
        {
            try
            {
                await _client.ConnectAsync(stoppingToken);
            }
            catch (OperationCanceledException) when (stoppingToken.IsCancellationRequested)
            {
                break;
            }
            catch (Exception ex)
            {
                LastError = $"{ex.GetType().Name}: {ex.Message}";
                _log.Write($"CONNECTION FAILED: {LastError}");
                _logger.LogError(ex, "Connection failed: {Error}", LastError);
            }

            try
            {
                await Task.Delay(10000, stoppingToken);
            }
            catch (OperationCanceledException)
            {
                break;
            }
        }

        _log.Write("Worker stopped");
    }

    public override async Task StopAsync(CancellationToken cancellationToken)
    {
        _log.Write("Worker stopping...");
        _logger.LogInformation("SIP worker stopping...");
        try { await _client.DisconnectAsync(); } catch { }
        await base.StopAsync(cancellationToken);
    }
}
