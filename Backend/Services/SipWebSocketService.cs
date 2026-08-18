using System.Net.WebSockets;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;
using DialerAPI.Models;

namespace DialerAPI.Services;

public class SipWebSocketService : BackgroundService
{
    private readonly CallSessionManager _sessionManager;
    private readonly ILogger<SipWebSocketService> _logger;
    private readonly IConfiguration _configuration;

    private ClientWebSocket? _ws;
    private bool _registered;
    private int _cseq;
    private string _tag = "";
    private string _branch = "";
    private string _callId = "";

    private const string SipServer = "sip2.cloud-connect.in";
    private const int SipPort = 7443;
    private const string SipUser = "102724701";
    private const string SipPassword = "Tejas@001";
    private const string SipRealm = "sip2.cloud-connect.in";

    private static readonly Random Rng = new();

    public SipWebSocketService(
        CallSessionManager sessionManager,
        ILogger<SipWebSocketService> logger,
        IConfiguration configuration)
    {
        _sessionManager = sessionManager;
        _logger = logger;
        _configuration = configuration;
    }

    public bool IsRegistered => _registered;

    public int Cseq => Interlocked.Increment(ref _cseq);

    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        _logger.LogInformation("SIP WebSocket service starting...");

        while (!stoppingToken.IsCancellationRequested)
        {
            try
            {
                await ConnectAndHandle(stoppingToken);
            }
            catch (OperationCanceledException) when (stoppingToken.IsCancellationRequested)
            {
                break;
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "SIP WebSocket connection error. Reconnecting in 5s...");
                _registered = false;
                await Task.Delay(TimeSpan.FromSeconds(5), stoppingToken);
            }
        }
    }

    private async Task ConnectAndHandle(CancellationToken ct)
    {
        _ws?.Dispose();
        _ws = new ClientWebSocket();
        _ws.Options.KeepAliveInterval = TimeSpan.FromSeconds(30);
        _ws.Options.RemoteCertificateValidationCallback = (_, _, _, _) => true;
        _ws.Options.AddSubProtocol("sip");
        _ws.Options.SetRequestHeader("Origin", $"https://{SipServer}:{SipPort}");

        var uri = new Uri($"wss://{SipServer}:{SipPort}");
        _logger.LogInformation("Connecting to SIP server: {Uri}", uri);

        await _ws.ConnectAsync(uri, ct);
        _logger.LogInformation("WebSocket connected to {Uri}", uri);

        _registered = false;
        await SendRegister(ct);

        var buffer = new byte[8192];
        var messageBuffer = new StringBuilder();

        while (_ws.State == WebSocketState.Open && !ct.IsCancellationRequested)
        {
            var result = await _ws.ReceiveAsync(new ArraySegment<byte>(buffer), ct);

            if (result.MessageType == WebSocketMessageType.Close)
            {
                _logger.LogWarning("Server closed connection: {Status}", result.CloseStatus);
                await _ws.CloseAsync(WebSocketCloseStatus.NormalClosure, "Closing", CancellationToken.None);
                _registered = false;
                break;
            }

            var chunk = Encoding.UTF8.GetString(buffer, 0, result.Count);
            messageBuffer.Append(chunk);

            if (result.EndOfMessage)
            {
                var message = messageBuffer.ToString();
                messageBuffer.Clear();
                await ProcessSipMessage(message, ct);
            }
        }

        _registered = false;
    }

    private async Task SendRegister(CancellationToken ct)
    {
        _cseq = 1;
        _tag = $"tag{Rng.Next(100000, 999999)}";
        _branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        _callId = Guid.NewGuid().ToString("N");

        var register = BuildSipMessage(
            "REGISTER", $"sip:{SipServer}",
            $"\"{SipUser}\" <sip:{SipUser}@{SipServer}>",
            $"<sip:{SipUser}@{SipServer}>",
            $"<sip:{SipUser}@{SipServer};transport=ws;expires=3600>",
            additionalHeaders: new[]
            {
                "Expires: 3600",
                "Allow: INVITE, ACK, CANCEL, BYE, REGISTER, OPTIONS, INFO, MESSAGE, SUBSCRIBE, NOTIFY"
            },
            body: "");

        await SendSipMessage(register, ct);
        _logger.LogInformation("Sent SIP REGISTER");
    }

    public async Task<bool> SendInvite(string callee, string callId, CancellationToken ct)
    {
        if (_ws?.State != WebSocketState.Open)
        {
            _logger.LogError("Cannot send INVITE - WebSocket not connected");
            return false;
        }

        var fromTag = $"tag{Rng.Next(100000, 999999)}";
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var inviteCallId = Guid.NewGuid().ToString("N");

        var invite = BuildSipMessage(
            "INVITE", $"sip:{callee}@{SipServer}",
            $"\"{SipUser}\" <sip:{SipUser}@{SipServer}>;tag={fromTag}",
            $"<sip:{callee}@{SipServer}>",
            $"<sip:{SipUser}@{SipServer};transport=ws>",
            callIdOverride: inviteCallId,
            branchOverride: branch,
            additionalHeaders: new[]
            {
                "Allow: INVITE, ACK, CANCEL, BYE, REGISTER, OPTIONS, INFO, MESSAGE, SUBSCRIBE, NOTIFY",
                "Supported: replaces, norefersub, eventlist, timer",
                "Content-Type: application/sdp"
            },
            body: GetOfferSdp());

        _sessionManager.MapSipCallId(callId, inviteCallId);
        await SendSipMessage(invite, ct);
        _logger.LogInformation("Sent SIP INVITE for {Callee}, callId={CallId}", callee, callId);
        return true;
    }

    public async Task<bool> SendBye(string callId, CancellationToken ct)
    {
        var session = _sessionManager.GetSession(callId);
        if (session == null || string.IsNullOrEmpty(session.SipCallId))
        {
            _logger.LogWarning("Cannot send BYE - no session or SIP call ID for {CallId}", callId);
            return false;
        }

        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var fromTag = $"tag{Rng.Next(100000, 999999)}";
        var toTag = $"tag{Rng.Next(100000, 999999)}";

        var bye = BuildSipMessage(
            "BYE", $"sip:{session.Callee}@{SipServer}",
            $"\"{SipUser}\" <sip:{SipUser}@{SipServer}>;tag={fromTag}",
            $"<sip:{session.Callee}@{SipServer}>;tag={toTag}",
            $"<sip:{session.Callee}@{SipServer}>",
            callIdOverride: session.SipCallId,
            branchOverride: branch);

        await SendSipMessage(bye, ct);
        _logger.LogInformation("Sent SIP BYE for callId={CallId}", callId);
        return true;
    }

    private async Task SendAck(string sdpResponse, CallSession session, CancellationToken ct)
    {
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";

        var ack = BuildSipMessage(
            "ACK", $"sip:{session.Callee}@{SipServer}",
            $"\"{SipUser}\" <sip:{SipUser}@{SipServer}>;tag={_tag}",
            $"<sip:{session.Callee}@{SipServer}>;tag={_tag}",
            $"<sip:{session.Callee}@{SipServer}>",
            callIdOverride: session.SipCallId,
            branchOverride: branch);

        await SendSipMessage(ack, ct);
        _logger.LogInformation("Sent SIP ACK for callId={CallId}", session.CallId);
    }

    private async Task SendSipMessage(string message, CancellationToken ct)
    {
        if (_ws?.State != WebSocketState.Open) return;

        // Log the full SIP message being sent
        _logger.LogInformation("Sending SIP message:\n{Message}", message);

        var bytes = Encoding.UTF8.GetBytes(message);
        var segment = new ArraySegment<byte>(bytes);
        await _ws.SendAsync(segment, WebSocketMessageType.Text, true, ct);
    }

    private async Task ProcessSipMessage(string message, CancellationToken ct)
    {
        var lines = message.Split("\r\n");
        if (lines.Length == 0) return;

        var startLine = lines[0];
        _logger.LogInformation("Received SIP message:\n{Message}", message);

        var callIdHeader = ExtractHeader(lines, "Call-ID:");
        var cseqHeader = ExtractHeader(lines, "CSeq:");
        var contentType = ExtractHeader(lines, "Content-Type:");
        var bodyStart = message.IndexOf("\r\n\r\n");
        var body = bodyStart >= 0 ? message[(bodyStart + 4)..] : "";

        if (startLine.StartsWith("SIP/2.0 200") && cseqHeader.Contains("REGISTER"))
        {
            _registered = true;
            _logger.LogInformation("SIP REGISTER successful");
        }
        else if (startLine.StartsWith("SIP/2.0 401") || startLine.StartsWith("SIP/2.0 407"))
        {
            var wwwAuth = ExtractHeader(lines, "WWW-Authenticate:") ?? ExtractHeader(lines, "Proxy-Authenticate:");
            if (wwwAuth != null && cseqHeader.Contains("REGISTER"))
            {
                await SendRegisterWithAuth(wwwAuth, ct);
            }
        }
        else if (startLine.StartsWith("SIP/2.0 100"))
        {
            _logger.LogDebug("SIP 100 Trying");
        }
        else if (startLine.StartsWith("SIP/2.0 180") || startLine.StartsWith("SIP/2.0 183"))
        {
            var session = FindSessionBySipCallId(callIdHeader);
            if (session != null)
            {
                _sessionManager.UpdateState(session.CallId, CallSessionState.Ringing);
                _logger.LogInformation("Call {CallId} is ringing", session.CallId);
            }
        }
        else if (startLine.StartsWith("SIP/2.0 200") && !cseqHeader.Contains("REGISTER"))
        {
            var session = FindSessionBySipCallId(callIdHeader);
            if (session != null)
            {
                if (cseqHeader.Contains("INVITE"))
                {
                    _sessionManager.UpdateState(session.CallId, CallSessionState.Connected);
                    await SendAck(body, session, ct);
                    _logger.LogInformation("Call {CallId} connected", session.CallId);
                }
                else if (cseqHeader.Contains("BYE"))
                {
                    _sessionManager.EndSession(session.CallId);
                    _logger.LogInformation("Call {CallId} ended (BYE OK)", session.CallId);
                }
            }
        }
        else if (startLine.StartsWith("SIP/2.0 486") || startLine.StartsWith("SIP/2.0 603"))
        {
            var session = FindSessionBySipCallId(callIdHeader);
            if (session != null)
            {
                _sessionManager.UpdateState(session.CallId, CallSessionState.Failed);
                _logger.LogInformation("Call {CallId} busy/declined", session.CallId);
            }
        }
        else if (startLine.StartsWith("SIP/2.0 48") || startLine.StartsWith("SIP/2.0 50") || startLine.StartsWith("SIP/2.0 6"))
        {
            var session = FindSessionBySipCallId(callIdHeader);
            if (session != null)
            {
                _sessionManager.UpdateState(session.CallId, CallSessionState.Failed);
                _logger.LogWarning("Call {CallId} failed: {Status}", session.CallId, startLine);
            }
        }
    }

    private async Task SendRegisterWithAuth(string wwwAuthHeader, CancellationToken ct)
    {
        var realmMatch = Regex.Match(wwwAuthHeader, "realm=\"([^\"]+)\"");
        var nonceMatch = Regex.Match(wwwAuthHeader, "nonce=\"([^\"]+)\"");
        var algorithmMatch = Regex.Match(wwwAuthHeader, "algorithm=([^,]+)");
        var qopMatch = Regex.Match(wwwAuthHeader, "qop=\"([^\"]+)\"");

        var realm = realmMatch.Success ? realmMatch.Groups[1].Value : SipRealm;
        var nonce = nonceMatch.Success ? nonceMatch.Groups[1].Value : "";
        var algorithm = algorithmMatch.Success ? algorithmMatch.Groups[1].Value : "MD5";
        var qop = qopMatch.Success ? qopMatch.Groups[1].Value : "";

        var ha1 = ComputeMd5Hash($"{SipUser}:{realm}:{SipPassword}");
        var ha2 = ComputeMd5Hash($"REGISTER:sip:{SipServer}");
        var nc = "00000001";
        var cnonce = Guid.NewGuid().ToString("N")[..8];

        string response;
        if (!string.IsNullOrEmpty(qop))
        {
            response = ComputeMd5Hash($"{ha1}:{nonce}:{nc}:{cnonce}:{qop}:{ha2}");
        }
        else
        {
            response = ComputeMd5Hash($"{ha1}:{nonce}:{ha2}");
        }

        var authHeader = qop switch
        {
            not null and not "" =>
                $"Digest username=\"{SipUser}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"sip:{SipServer}\", " +
                $"response=\"{response}\", algorithm={algorithm}, cnonce=\"{cnonce}\", nc={nc}, qop={qop}",
            _ =>
                $"Digest username=\"{SipUser}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"sip:{SipServer}\", " +
                $"response=\"{response}\", algorithm={algorithm}"
        };

        _cseq = 1;
        _tag = $"tag{Rng.Next(100000, 999999)}";
        _branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        _callId = Guid.NewGuid().ToString("N");

        var register = BuildSipMessage(
            "REGISTER", $"sip:{SipServer}",
            $"\"{SipUser}\" <sip:{SipUser}@{SipServer}>",
            $"<sip:{SipUser}@{SipServer}>",
            $"<sip:{SipUser}@{SipServer};transport=ws;expires=3600>",
            additionalHeaders: new[]
            {
                "Expires: 3600",
                $"Authorization: {authHeader}",
                "Allow: INVITE, ACK, CANCEL, BYE, REGISTER, OPTIONS, INFO, MESSAGE, SUBSCRIBE, NOTIFY"
            },
            body: "");

        await SendSipMessage(register, ct);
        _logger.LogInformation("Sent SIP REGISTER with auth");
    }

    private string BuildSipMessage(
        string method, string requestUri,
        string from, string to, string contact,
        string? callIdOverride = null,
        string? branchOverride = null,
        string[]? additionalHeaders = null,
        string body = "")
    {
        var callId = callIdOverride ?? _callId;
        var branch = branchOverride ?? $"z9hG4bK{Rng.Next(100000, 999999)}";
        var cseq = Cseq;
        var contentLength = Encoding.UTF8.GetByteCount(body);

        var sb = new StringBuilder();
        sb.AppendLine($"{method} {requestUri} SIP/2.0");
        sb.AppendLine($"Via: SIP/2.0/WSS {SipServer}:{SipPort};branch={branch};rport");
        sb.AppendLine($"Max-Forwards: 70");
        sb.AppendLine($"From: {from}");
        sb.AppendLine($"To: {to}");
        sb.AppendLine($"Contact: {contact}");
        sb.AppendLine($"Call-ID: {callId}");
        sb.AppendLine($"CSeq: {cseq} {method}");
        sb.AppendLine($"User-Agent: DialerBackend/1.0");

        if (additionalHeaders != null)
        {
            foreach (var h in additionalHeaders)
                sb.AppendLine(h);
        }

        sb.AppendLine($"Content-Length: {contentLength}");

        if (!string.IsNullOrEmpty(body))
        {
            sb.AppendLine();
            sb.Append(body);
        }
        else
        {
            sb.AppendLine();
        }

        return sb.ToString();
    }

    private static string ExtractHeader(string[] lines, string prefix)
    {
        foreach (var line in lines)
        {
            if (line.StartsWith(prefix, StringComparison.OrdinalIgnoreCase))
                return line[prefix.Length..].Trim();
        }
        return "";
    }

    private CallSession? FindSessionBySipCallId(string sipCallId)
    {
        if (string.IsNullOrEmpty(sipCallId)) return null;
        return _sessionManager.GetSessionBySipCallId(sipCallId);
    }

    private static string ComputeMd5Hash(string input)
    {
        var bytes = MD5.HashData(Encoding.UTF8.GetBytes(input));
        return Convert.ToHexStringLower(bytes);
    }

    private static string GetOfferSdp()
    {
        return "v=0\r\n" +
               $"o={SipUser} 0 0 IN IP4 {SipServer}\r\n" +
               "s=call\r\n" +
               "c=IN IP4 0.0.0.0\r\n" +
               "t=0 0\r\n" +
               "m=audio 0 RTP/AVP 0 8 101\r\n" +
               "a=rtpmap:0 PCMU/8000\r\n" +
               "a=rtpmap:8 PCMA/8000\r\n" +
               "a=rtpmap:101 telephone-event/8000\r\n" +
               "a=sendrecv\r\n";
    }

    public override async Task StopAsync(CancellationToken cancellationToken)
    {
        _logger.LogInformation("SIP WebSocket service stopping...");
        if (_ws?.State == WebSocketState.Open)
        {
            await _ws.CloseAsync(WebSocketCloseStatus.NormalClosure, "Service stopping", CancellationToken.None);
        }
        _ws?.Dispose();
        await base.StopAsync(cancellationToken);
    }
}
