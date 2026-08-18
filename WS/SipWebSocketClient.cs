using System.Net.WebSockets;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;
using Microsoft.Extensions.Logging;

namespace WsClient;

public class SipWebSocketClient : IDisposable
{
    private readonly string _server;
    private readonly int _port;
    private readonly string _username;
    private readonly string _password;
    private readonly string _realm;
    private readonly string _userAgent;
    private readonly bool _useTls;
    private readonly ILogger<SipWebSocketClient>? _logger;
    private readonly LogBuffer? _log;

    private ClientWebSocket? _ws;
    private bool _registered;
    private int _cseq;
    private string _tag = "";
    private string _branch = "";
    private string _callId = "";
    private string _activeCallee = "";
    private string _activeCallId = "";
    private string _inviteTag = "";
    private int _inviteCseq;
    private bool _inCall;
    private bool _callAnswered;
    private readonly string _clientId = Guid.NewGuid().ToString("N")[..12];
    private readonly string _localIp = "160.250.28.172";
    private CancellationTokenSource? _keepaliveCts;

    private static readonly Random Rng = new();

    public bool IsRegistered => _registered;
    public bool InCall => _inCall;
    public string ActiveCallee => _activeCallee;
    public string ActiveCallId => _activeCallId;

    public event Action? OnRegistered;
    public event Action<string>? OnMessageReceived;
    public event Action<string>? OnError;
    public event Action? OnDisconnected;
    public event Action<string>? OnCallRinging;
    public event Action<string>? OnCallAnswered;
    public event Action<string, string>? OnCallFailed;
    public event Action<string>? OnCallEnded;
    public event Action<string, int, string>? OnMediaInfoReady;

    public SipWebSocketClient(
        string server = "sip2.cloud-connect.in",
        int port = 7443,
        string username = "102724701",
        string password = "Tejas@001",
        string realm = "sip2.cloud-connect.in",
        string userAgent = "PJSUA",
        bool useTls = true,
        ILogger<SipWebSocketClient>? logger = null,
        LogBuffer? log = null)
    {
        _server = server;
        _port = port;
        _username = username;
        _password = password;
        _realm = realm;
        _userAgent = userAgent;
        _useTls = useTls;
        _logger = logger;
        _log = log;
    }

    public async Task ConnectAsync(CancellationToken ct = default)
    {
        _ws?.Dispose();
        _ws = new ClientWebSocket();
        _ws.Options.KeepAliveInterval = TimeSpan.FromSeconds(30);
        _ws.Options.AddSubProtocol("sip");
        _ws.Options.SetRequestHeader("Origin", $"https://{_server}:{_port}");

        if (_useTls)
        {
            _ws.Options.RemoteCertificateValidationCallback = (_, _, _, _) => true;
        }

        var scheme = _useTls ? "wss" : "ws";
        var uri = new Uri($"{scheme}://{_server}:{_port}");
        _log?.Write($"Connecting to {uri}");
        _logger?.LogInformation($"Connecting to {uri}");

        var cts = new CancellationTokenSource(TimeSpan.FromSeconds(15));
        var linked = CancellationTokenSource.CreateLinkedTokenSource(ct, cts.Token);
        try
        {
            await _ws.ConnectAsync(uri, linked.Token);
        }
        finally
        {
            linked.Dispose();
            cts.Dispose();
        }

        _log?.Write("Connected, sending REGISTER...");

        await SendRegisterAsync(ct);
        await ReceiveLoopAsync(ct);
    }

    private async Task ReceiveLoopAsync(CancellationToken ct)
    {
        var buffer = new byte[8192];
        var messageBuffer = new StringBuilder();

        while (_ws?.State == WebSocketState.Open && !ct.IsCancellationRequested)
        {
            var result = await _ws.ReceiveAsync(new ArraySegment<byte>(buffer), ct);

            if (result.MessageType == WebSocketMessageType.Close)
            {
                _logger?.LogWarning($"Server closed: {result.CloseStatus}");
                _log?.Write($"Server closed: {result.CloseStatus}");
                _registered = false;
                StopKeepalive();
                OnDisconnected?.Invoke();
                break;
            }

            messageBuffer.Append(Encoding.UTF8.GetString(buffer, 0, result.Count));

            if (result.EndOfMessage)
            {
                var message = messageBuffer.ToString();
                messageBuffer.Clear();
                HandleMessage(message);
            }
        }

        _logger?.LogWarning("Receive loop ended — WebSocket state: {State}", _ws?.State.ToString() ?? "null");
        _registered = false;
        StopKeepalive();
    }

    private void HandleMessage(string message)
    {
        var lines = message.Split("\r\n");
        if (lines.Length == 0) return;

        var startLine = lines[0];
        _log?.SipResponse(message);
        _logger?.LogInformation("Received SIP message:\n{Message}", message);

        var cseqHeader = ExtractHeader(lines, "CSeq:");
        var callIdHeader = ExtractHeader(lines, "Call-ID:");

        OnMessageReceived?.Invoke(message);

        if (startLine.StartsWith("SIP/2.0 200") && cseqHeader.Contains("REGISTER"))
        {
            _registered = true;
            _log?.Write("REGISTER successful!");
            _logger?.LogInformation("REGISTER 200 OK received — starting keepalive");
            StartKeepalive();
            OnRegistered?.Invoke();
        }
        else if (startLine.StartsWith("SIP/2.0 401") || startLine.StartsWith("SIP/2.0 407"))
        {
            var auth = ExtractHeader(lines, "WWW-Authenticate:");
            if (string.IsNullOrEmpty(auth))
                auth = ExtractHeader(lines, "Proxy-Authenticate:");
            if (!string.IsNullOrEmpty(auth) && cseqHeader.Contains("REGISTER"))
            {
                _ = SendRegisterWithAuthAsync(auth);
            }
            else if (!string.IsNullOrEmpty(auth) && cseqHeader.Contains("INVITE"))
            {
                var callIdForAuth = !string.IsNullOrEmpty(callIdHeader) ? callIdHeader : _activeCallId;
                _ = SendInviteWithAuthAsync(auth, callIdForAuth);
            }
        }
        else if (startLine.StartsWith("SIP/2.0 100"))
        {
            _log?.Write("100 Trying");
        }
        else if (startLine.StartsWith("SIP/2.0 180") || startLine.StartsWith("SIP/2.0 183"))
        {
            _log?.Write("180/183 Ringing");
            OnCallRinging?.Invoke(callIdHeader);
            var rseq = ExtractHeader(lines, "RSeq:");
            var rack = ExtractHeader(lines, "RAck:");
            if (!string.IsNullOrEmpty(rseq) && !string.IsNullOrEmpty(callIdHeader))
            {
                _ = SendPrackAsync(callIdHeader, cseqHeader, rseq);
            }

            var mediaInfo = ParseMediaFromSdp(message);
            if (mediaInfo != null)
            {
                _log?.Write($"Remote media (from 183): {mediaInfo.Value.Ip}:{mediaInfo.Value.Port} codec={mediaInfo.Value.Codec}");
                _logger?.LogInformation("Remote media (from 183): {Ip}:{Port} codec={Codec}", mediaInfo.Value.Ip, mediaInfo.Value.Port, mediaInfo.Value.Codec);
                OnMediaInfoReady?.Invoke(mediaInfo.Value.Ip, mediaInfo.Value.Port, mediaInfo.Value.Codec);
            }
        }
        else if (startLine.StartsWith("SIP/2.0 200") && cseqHeader.Contains("INVITE"))
        {
            if (!_callAnswered)
            {
                _callAnswered = true;
                _log?.Write("200 OK (INVITE) — call answered");
                OnCallAnswered?.Invoke(callIdHeader);
                var mediaInfo = ParseMediaFromSdp(message);
                if (mediaInfo != null)
                {
                    _log?.Write($"Remote media: {mediaInfo.Value.Ip}:{mediaInfo.Value.Port} codec={mediaInfo.Value.Codec}");
                    _logger?.LogInformation("Remote media: {Ip}:{Port} codec={Codec}", mediaInfo.Value.Ip, mediaInfo.Value.Port, mediaInfo.Value.Codec);
                    OnMediaInfoReady?.Invoke(mediaInfo.Value.Ip, mediaInfo.Value.Port, mediaInfo.Value.Codec);
                }
            }
            _ = SendAckAsync(message, callIdHeader);
        }
        else if (startLine.StartsWith("SIP/2.0 200") && cseqHeader.Contains("BYE"))
        {
            _log?.Write("200 OK (BYE) — call ended");
            OnCallEnded?.Invoke(callIdHeader);
        }
        else if (startLine.StartsWith("SIP/2.0 486") || startLine.StartsWith("SIP/2.0 603"))
        {
            _log?.Write($"Call rejected/busy: {startLine}");
            _ = SendAckAsync(message, callIdHeader);
            _inCall = false;
            OnCallFailed?.Invoke(callIdHeader, startLine);
            OnError?.Invoke(startLine);
        }
        else if (startLine.StartsWith("SIP/2.0 4") || startLine.StartsWith("SIP/2.0 5") || startLine.StartsWith("SIP/2.0 6"))
        {
            _log?.Write($"Error: {startLine}");
            _ = SendAckAsync(message, callIdHeader);
            _inCall = false;
            OnCallFailed?.Invoke(callIdHeader, startLine);
            OnError?.Invoke(startLine);
        }
        else if (startLine.StartsWith("BYE "))
        {
            _log?.Write("Incoming BYE — call ended by remote");
            _ = SendByeResponseAsync(callIdHeader);
            _inCall = false;
            OnCallEnded?.Invoke(callIdHeader);
        }
    }

    public async Task SendInviteAsync(string callee)
    {
        var fromTag = $"tag{Rng.Next(100000, 999999)}";
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var inviteCallId = Guid.NewGuid().ToString("N");

        var invite = BuildMessage(
            "INVITE", $"sip:{callee}@{_realm}",
            $"\"{_username}\" <sip:{_username}@{_realm}>;tag={fromTag}",
            $"<sip:{callee}@{_realm}>",
            $"<sip:{_username}@{_localIp};transport=ws>",
            inviteCallId, branch,
            new[] { "Allow: INVITE, ACK, CANCEL, BYE, REGISTER, OPTIONS, INFO, MESSAGE, SUBSCRIBE, NOTIFY",
                    "Content-Type: application/sdp" },
            GetOfferSdp());

        await SendAsync(invite);
        _activeCallee = callee;
        _activeCallId = inviteCallId;
        _inviteTag = fromTag;
        _inviteCseq = _cseq;
        _inCall = true;
        _callAnswered = false;
        _log?.Write($">> INVITE sip:{callee}@{_realm}");
    }

    public async Task SendByeAsync(string callee, string sipCallId)
    {
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var fromTag = !string.IsNullOrEmpty(_inviteTag) ? _inviteTag : _tag;

        var bye = BuildMessage(
            "BYE", $"sip:{callee}@{_realm}",
            $"\"{_username}\" <sip:{_username}@{_realm}>;tag={fromTag}",
            $"<sip:{callee}@{_realm}>",
            $"<sip:{_username}@{_localIp};transport=ws>",
            sipCallId, branch);

        await SendAsync(bye);
        _inCall = false;
        _activeCallee = "";
        _activeCallId = "";
        _inviteTag = "";
        _log?.Write($">> BYE sip:{callee}@{_realm}");
    }

    private async Task SendInviteWithAuthAsync(string proxyAuth, string? callId)
    {
        var realm = Rx(proxyAuth, "realm=\"([^\"]+)\"") ?? _realm;
        var nonce = Rx(proxyAuth, "nonce=\"([^\"]+)\"") ?? "";
        var algorithm = Rx(proxyAuth, "algorithm=([^, ]+)") ?? "MD5";
        var qop = Rx(proxyAuth, "qop=\"([^\"]+)\"") ?? "";

        var ha1 = Md5($"{_username}:{realm}:{_password}");
        var ha2 = Md5($"INVITE:sip:{_activeCallee}@{_realm}");
        var nc = "00000001";
        var cnonce = Guid.NewGuid().ToString("N")[..8];
        var response = !string.IsNullOrEmpty(qop)
            ? Md5($"{ha1}:{nonce}:{nc}:{cnonce}:{qop}:{ha2}")
            : Md5($"{ha1}:{nonce}:{ha2}");

        var authHeader = !string.IsNullOrEmpty(qop)
            ? $"Digest username=\"{_username}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"sip:{_activeCallee}@{_realm}\", response=\"{response}\", algorithm={algorithm}, cnonce=\"{cnonce}\", nc={nc}, qop={qop}"
            : $"Digest username=\"{_username}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"sip:{_activeCallee}@{_realm}\", response=\"{response}\", algorithm={algorithm}";

        var fromTag = !string.IsNullOrEmpty(_inviteTag) ? _inviteTag : $"tag{Rng.Next(100000, 999999)}";
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var inviteCallId = !string.IsNullOrEmpty(callId) ? callId : _activeCallId;
        if (string.IsNullOrEmpty(inviteCallId))
            inviteCallId = Guid.NewGuid().ToString("N");
        var to = $"<sip:{_activeCallee}@{_realm}>";

        var invite = BuildMessage(
            "INVITE", $"sip:{_activeCallee}@{_realm}",
            $"\"{_username}\" <sip:{_username}@{_realm}>;tag={fromTag}",
            to,
            $"<sip:{_username}@{_localIp};transport=ws>",
            inviteCallId, branch,
            new[] { "Proxy-Authorization: " + authHeader,
                    "Allow: INVITE, ACK, CANCEL, BYE, REGISTER, OPTIONS, INFO, MESSAGE, SUBSCRIBE, NOTIFY",
                    "Content-Type: application/sdp" },
            GetOfferSdp());

        await SendAsync(invite);
        _activeCallId = inviteCallId;
        _inviteTag = fromTag;
        _inviteCseq = _cseq;
        _log?.Write($">> INVITE (with proxy auth) sip:{_activeCallee}@{_realm}");
    }

    private async Task SendRegisterAsync(CancellationToken ct)
    {
        _cseq = 1;
        _tag = $"tag{Rng.Next(100000, 999999)}";
        _branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        _callId = Guid.NewGuid().ToString("N");

        var viaProtocol = _useTls ? "WSS" : "WS";
        var cseq = 1;
        var contactUri = $"<sip:{_username}@{_localIp};transport=ws;expires=3600>";
        var register =
            $"REGISTER sip:{_realm} SIP/2.0\r\n" +
            $"Via: SIP/2.0/{viaProtocol} {_localIp};branch={_branch};rport\r\n" +
            $"Max-Forwards: 70\r\n" +
            $"From: \"{_username}\" <sip:{_username}@{_realm}>;tag={_tag}\r\n" +
            $"To: <sip:{_username}@{_realm}>\r\n" +
            $"Contact: {contactUri}\r\n" +
            $"Call-ID: {_callId}\r\n" +
            $"CSeq: {cseq} REGISTER\r\n" +
            $"User-Agent: {_userAgent}\r\n" +
            $"Expires: 3600\r\n" +
            $"Content-Length: 0\r\n" +
            $"\r\n";

        await SendAsync(register, ct);
        _log?.Write(">> REGISTER");
    }

    private async Task SendDeregisterAsync(CancellationToken ct = default)
    {
        var tag = $"tag{Rng.Next(100000, 999999)}";
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var callId = Guid.NewGuid().ToString("N");
        var viaProtocol = _useTls ? "WSS" : "WS";

        var deregister =
            $"REGISTER sip:{_realm} SIP/2.0\r\n" +
            $"Via: SIP/2.0/{viaProtocol} {_localIp};branch={branch};rport\r\n" +
            $"Max-Forwards: 70\r\n" +
            $"From: \"{_username}\" <sip:{_username}@{_realm}>;tag={tag}\r\n" +
            $"To: <sip:{_username}@{_realm}>\r\n" +
            $"Contact: <sip:{_username}@{_localIp};transport=ws;expires=0>\r\n" +
            $"Call-ID: {callId}\r\n" +
            $"CSeq: 1 REGISTER\r\n" +
            $"User-Agent: {_userAgent}\r\n" +
            $"Expires: 0\r\n" +
            $"Content-Length: 0\r\n" +
            $"\r\n";

        try
        {
            _logger?.LogInformation("Sending de-REGISTER (Expires: 0) to clean up stale bindings");
            await SendAsync(deregister, ct);
            _log?.Write(">> DE-REGISTER (expires=0)");
        }
        catch (Exception ex)
        {
            _logger?.LogWarning(ex, "De-REGISTER failed (non-fatal)");
        }
    }

    private async Task SendRegisterWithAuthAsync(string wwwAuth)
    {
        var realm = Rx(wwwAuth, "realm=\"([^\"]+)\"") ?? _realm;
        var nonce = Rx(wwwAuth, "nonce=\"([^\"]+)\"") ?? "";
        var algorithm = Rx(wwwAuth, "algorithm=([^, ]+)") ?? "MD5";
        var qop = Rx(wwwAuth, "qop=\"([^\"]+)\"") ?? "";

        var ha1 = Md5($"{_username}:{realm}:{_password}");
        var ha2 = Md5($"REGISTER:sip:{_realm}");
        var nc = "00000001";
        var cnonce = Guid.NewGuid().ToString("N")[..8];

        var response = !string.IsNullOrEmpty(qop)
            ? Md5($"{ha1}:{nonce}:{nc}:{cnonce}:{qop}:{ha2}")
            : Md5($"{ha1}:{nonce}:{ha2}");

        var requestUri = $"sip:{_realm}";
        var authHeader = !string.IsNullOrEmpty(qop)
            ? $"Digest username=\"{_username}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"{requestUri}\", response=\"{response}\", algorithm={algorithm}, cnonce=\"{cnonce}\", nc={nc}, qop={qop}"
            : $"Digest username=\"{_username}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"{requestUri}\", response=\"{response}\", algorithm={algorithm}";

        _branch = $"z9hG4bK{Rng.Next(100000, 999999)}";

        var viaProtocol = _useTls ? "WSS" : "WS";
        var contactUri = $"<sip:{_username}@{_localIp};transport=ws;expires=3600>";
        var cseq = Interlocked.Increment(ref _cseq);

        var register =
            $"REGISTER {requestUri} SIP/2.0\r\n" +
            $"Via: SIP/2.0/{viaProtocol} {_localIp};branch={_branch};rport\r\n" +
            $"Max-Forwards: 70\r\n" +
            $"From: \"{_username}\" <sip:{_username}@{_realm}>;tag={_tag}\r\n" +
            $"To: <sip:{_username}@{_realm}>\r\n" +
            $"Contact: {contactUri}\r\n" +
            $"Call-ID: {_callId}\r\n" +
            $"CSeq: {cseq} REGISTER\r\n" +
            $"User-Agent: {_userAgent}\r\n" +
            $"Expires: 3600\r\n" +
            $"Authorization: {authHeader}\r\n" +
            $"Content-Length: 0\r\n" +
            $"\r\n";

        await SendAsync(register);
        _log?.Write(">> REGISTER (with auth)");
    }

    private void StartKeepalive()
    {
        _keepaliveCts?.Cancel();
        _keepaliveCts = new CancellationTokenSource();
        _ = KeepaliveLoopAsync(_keepaliveCts.Token);
    }

    private void StopKeepalive()
    {
        _keepaliveCts?.Cancel();
        _keepaliveCts = null;
    }

    private async Task KeepaliveLoopAsync(CancellationToken ct)
    {
        try
        {
            while (!ct.IsCancellationRequested)
            {
                await Task.Delay(20000, ct);
                if (_ws?.State == WebSocketState.Open)
                {
                    var ping = "\r\n\r\n";
                    var bytes = Encoding.UTF8.GetBytes(ping);
                    await _ws.SendAsync(new ArraySegment<byte>(bytes), WebSocketMessageType.Text, true, ct);
                    _logger?.LogDebug("Keepalive sent");
                }
            }
        }
        catch (OperationCanceledException) { }
    }

    private async Task SendAckAsync(string sdpResponse, string callId)
    {
        var lines = sdpResponse.Split("\r\n");
        var toHeader = ExtractHeader(lines, "To:");
        if (string.IsNullOrEmpty(toHeader))
            toHeader = ExtractHeader(lines, "t:");

        var fromTag = !string.IsNullOrEmpty(_inviteTag) ? _inviteTag : _tag;
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var requestUri = $"sip:{_activeCallee}@{_realm}";

        var viaProtocol = _useTls ? "WSS" : "WS";
        var via = $"Via: SIP/2.0/{viaProtocol} {_localIp};branch={branch};rport";

        var sb = new StringBuilder();
        sb.AppendLine($"ACK {requestUri} SIP/2.0");
        sb.AppendLine(via);
        sb.AppendLine("Max-Forwards: 70");
        sb.AppendLine($"From: \"{_username}\" <sip:{_username}@{_realm}>;tag={fromTag}");
        sb.AppendLine($"To: {toHeader}");
        sb.AppendLine($"Contact: <sip:{_username}@{_localIp};transport=ws>");
        sb.AppendLine($"Call-ID: {callId}");
        sb.AppendLine($"CSeq: {_inviteCseq} ACK");
        sb.AppendLine($"User-Agent: {_userAgent}");
        sb.AppendLine("Content-Length: 0");
        sb.AppendLine();

        await SendAsync(sb.ToString());
        _log?.Write($">> ACK (CSeq={_inviteCseq})");
    }

    private async Task SendByeResponseAsync(string callId)
    {
        var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
        var viaProtocol = _useTls ? "WSS" : "WS";

        var response =
            $"SIP/2.0 200 OK\r\n" +
            $"Via: SIP/2.0/{viaProtocol} {_localIp};branch={branch};rport\r\n" +
            $"From: \"{_username}\" <sip:{_username}@{_realm}>;tag={_inviteTag}\r\n" +
            $"To: \"{_username}\" <sip:{_username}@{_realm}>;tag=tag{Rng.Next(100000, 999999)}\r\n" +
            $"Call-ID: {callId}\r\n" +
            $"CSeq: 1 BYE\r\n" +
            $"User-Agent: {_userAgent}\r\n" +
            $"Content-Length: 0\r\n" +
            $"\r\n";

        await SendAsync(response);
        _log?.Write("<< 200 OK (BYE)");
    }

    private async Task SendPrackAsync(string callId, string cseqHeader, string rseq)
    {
        var cseqParts = cseqHeader.Split(' ');
        var cseqNum = cseqParts.Length > 0 ? cseqParts[0] : "1";
        var method = cseqParts.Length > 2 ? cseqParts[2] : "INVITE";

        var fromTag = !string.IsNullOrEmpty(_inviteTag) ? _inviteTag : _tag;
        var prack = BuildMessage(
            "PRACK", $"sip:{_activeCallee}@{_realm}",
            $"\"{_username}\" <sip:{_username}@{_realm}>;tag={fromTag}",
            $"<sip:{_activeCallee}@{_realm}>",
            $"<sip:{_username}@{_localIp};transport=ws>",
            callId, null,
            new[] { $"RAck: {rseq} {cseqNum} {method}" });

        await SendAsync(prack);
        _log?.Write($">> PRACK (RSeq={rseq})");
    }

    private string BuildMessage(string method, string requestUri, string from, string to,
        string contact, string? callIdOverride = null, string? branchOverride = null,
        string[]? additionalHeaders = null, string body = "")
    {
        var callId = callIdOverride ?? _callId;
        var branch = branchOverride ?? $"z9hG4bK{Rng.Next(100000, 999999)}";
        var cseq = Interlocked.Increment(ref _cseq);
        var contentLength = Encoding.UTF8.GetByteCount(body);
        var viaProtocol = _useTls ? "WSS" : "WS";
        var viaPort = _port;

        var sb = new StringBuilder();
        sb.AppendLine($"{method} {requestUri} SIP/2.0");
        sb.AppendLine($"Via: SIP/2.0/{viaProtocol} {_localIp};branch={branch};rport");
        sb.AppendLine("Max-Forwards: 70");
        sb.AppendLine($"From: {from}");
        sb.AppendLine($"To: {to}");
        sb.AppendLine($"Contact: {contact}");
        sb.AppendLine($"Call-ID: {callId}");
        sb.AppendLine($"CSeq: {cseq} {method}");
        sb.AppendLine($"User-Agent: {_userAgent}");
        sb.AppendLine("Supported: 100rel, timer, replaces, from-change, gruu");
        sb.AppendLine("Allow: INVITE, ACK, CANCEL, BYE, UPDATE, PRACK, REFER, OPTIONS, MESSAGE");

        if (additionalHeaders != null)
            foreach (var h in additionalHeaders) sb.AppendLine(h);

        sb.AppendLine($"Content-Length: {contentLength}");
        sb.AppendLine();
        if (!string.IsNullOrEmpty(body)) sb.Append(body);

        return sb.ToString();
    }

    private async Task SendAsync(string message, CancellationToken ct = default)
    {
        if (_ws?.State != WebSocketState.Open)
        {
            var state = _ws?.State.ToString() ?? "null";
            _logger?.LogWarning("Cannot send — WebSocket state is {State}", state);
            throw new InvalidOperationException($"Cannot send SIP message — WebSocket is not open (state={state})");
        }

        _logger?.LogInformation("Sending SIP message:\n{Message}", message);
        _log?.SipRequest("sent", message);

        var bytes = Encoding.UTF8.GetBytes(message);
        await _ws.SendAsync(new ArraySegment<byte>(bytes), WebSocketMessageType.Text, true, ct);
    }

    public async Task DisconnectAsync()
    {
        _registered = false;
        StopKeepalive();
        if (_ws?.State == WebSocketState.Open)
            await _ws.CloseAsync(WebSocketCloseStatus.NormalClosure, "Client closing", CancellationToken.None);
        _ws?.Dispose();
        _ws = null;
    }

    public void Dispose() { _ws?.Dispose(); }

    public async Task<List<SipEvent>> RegisterOnceAsync(string username, string password, CancellationToken ct)
    {
        var events = new List<SipEvent>();
        var clientId = Guid.NewGuid().ToString("N")[..12];

        using var ws = new ClientWebSocket();
        ws.Options.AddSubProtocol("sip");
        ws.Options.SetRequestHeader("Origin", $"https://{_server}:{_port}");
        if (_useTls)
            ws.Options.RemoteCertificateValidationCallback = (_, _, _, _) => true;

        var scheme = _useTls ? "wss" : "ws";
        await ws.ConnectAsync(new Uri($"{scheme}://{_server}:{_port}"), ct);

        async Task SendReg(bool withAuth, string? wwwAuth = null)
        {
            var tag = $"tag{Rng.Next(100000, 999999)}";
            var branch = $"z9hG4bK{Rng.Next(100000, 999999)}";
            var callId = Guid.NewGuid().ToString("N");
            string msg;

            if (withAuth && wwwAuth != null)
            {
                var realm = Rx(wwwAuth, "realm=\"([^\"]+)\"") ?? _realm;
                var nonce = Rx(wwwAuth, "nonce=\"([^\"]+)\"") ?? "";
                var algorithm = Rx(wwwAuth, "algorithm=([^, ]+)") ?? "MD5";
                var qop = Rx(wwwAuth, "qop=\"([^\"]+)\"") ?? "";

                var ha1 = Md5($"{username}:{realm}:{password}");
                var ha2 = Md5($"REGISTER:sip:{_realm}");
                var nc = "00000001";
                var cnonce = Guid.NewGuid().ToString("N")[..8];
                var response = !string.IsNullOrEmpty(qop)
                    ? Md5($"{ha1}:{nonce}:{nc}:{cnonce}:{qop}:{ha2}")
                    : Md5($"{ha1}:{nonce}:{ha2}");

                var requestUri = $"sip:{_realm}";
                var authHeader = !string.IsNullOrEmpty(qop)
                    ? $"Digest username=\"{username}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"{requestUri}\", response=\"{response}\", algorithm={algorithm}, cnonce=\"{cnonce}\", nc={nc}, qop={qop}"
                    : $"Digest username=\"{username}\", realm=\"{realm}\", nonce=\"{nonce}\", uri=\"{requestUri}\", response=\"{response}\", algorithm={algorithm}";

                msg =
                    $"REGISTER {requestUri} SIP/2.0\r\n" +
                    $"Via: SIP/2.0/{(_useTls ? "WSS" : "WS")} {_localIp};branch={branch};rport\r\n" +
                    $"Max-Forwards: 70\r\n" +
                    $"From: \"{username}\" <sip:{username}@{_realm}>;tag={tag}\r\n" +
                    $"To: <sip:{username}@{_realm}>\r\n" +
                    $"Contact: <sip:{username}@{_localIp};transport=ws;expires=3600>\r\n" +
                    $"Call-ID: {callId}\r\n" +
                    $"CSeq: 1 REGISTER\r\n" +
                    $"User-Agent: {_userAgent}\r\n" +
                    $"Expires: 3600\r\n" +
                    $"Authorization: {authHeader}\r\n" +
                    $"Content-Length: 0\r\n\r\n";
            }
            else
            {
                msg =
                    $"REGISTER sip:{_realm} SIP/2.0\r\n" +
                    $"Via: SIP/2.0/{(_useTls ? "WSS" : "WS")} {_localIp};branch={branch};rport\r\n" +
                    $"Max-Forwards: 70\r\n" +
                    $"From: \"{username}\" <sip:{username}@{_realm}>;tag={tag}\r\n" +
                    $"To: <sip:{username}@{_realm}>\r\n" +
                    $"Contact: <sip:{username}@{_localIp};transport=ws;expires=3600>\r\n" +
                    $"Call-ID: {callId}\r\n" +
                    $"CSeq: 1 REGISTER\r\n" +
                    $"User-Agent: {_userAgent}\r\n" +
                    $"Expires: 3600\r\n" +
                    $"Content-Length: 0\r\n\r\n";
            }

            events.Add(ParseSip(message: msg, direction: "sent"));
            var bytes = Encoding.UTF8.GetBytes(msg);
            await ws.SendAsync(new ArraySegment<byte>(bytes), WebSocketMessageType.Text, true, ct);
        }

        await SendReg(false);

        var buffer = new byte[8192];
        var sb = new StringBuilder();

        while (ws.State == WebSocketState.Open && !ct.IsCancellationRequested)
        {
            var result = await ws.ReceiveAsync(new ArraySegment<byte>(buffer), ct);
            if (result.MessageType == WebSocketMessageType.Close) break;

            sb.Append(Encoding.UTF8.GetString(buffer, 0, result.Count));
            if (result.EndOfMessage)
            {
                var msg = sb.ToString();
                sb.Clear();
                events.Add(ParseSip(message: msg, direction: "received"));

                if (msg.StartsWith("SIP/2.0 401") || msg.StartsWith("SIP/2.0 407"))
                {
                    var authH = ExtractHeader(msg.Split("\r\n"), "WWW-Authenticate:");
                    if (string.IsNullOrEmpty(authH))
                        authH = ExtractHeader(msg.Split("\r\n"), "Proxy-Authenticate:");
                    await SendReg(true, authH);
                }
                else if (msg.StartsWith("SIP/2.0 200") || msg.StartsWith("SIP/2.0 403") || msg.StartsWith("SIP/2.0 408"))
                {
                    break;
                }
            }
        }

        return events;
    }

    public static SipEvent ParseSip(string message, string direction)
    {
        var lines = message.TrimEnd('\r', '\n').Split("\r\n");
        var startLine = lines.Length > 0 ? lines[0] : "";
        var headers = new Dictionary<string, string>();
        foreach (var line in lines.Skip(1))
        {
            var idx = line.IndexOf(':');
            if (idx > 0)
                headers[line[..idx].Trim()] = line[(idx + 1)..].Trim();
        }

        var bodyStart = Array.IndexOf(lines, "");
        var body = bodyStart >= 0 && bodyStart < lines.Length - 1
            ? string.Join("\r\n", lines.Skip(bodyStart + 1))
            : "";

        return new SipEvent
        {
            Timestamp = DateTime.UtcNow,
            Direction = direction,
            StartLine = startLine,
            Headers = headers,
            Body = body
        };
    }

    private static string ExtractHeader(string[] lines, string prefix)
    {
        var compact = prefix switch
        {
            "Call-ID:" => "i:",
            "From:" => "f:",
            "To:" => "t:",
            "Via:" => "v:",
            _ => null
        };
        foreach (var line in lines)
        {
            if (line.StartsWith(prefix, StringComparison.OrdinalIgnoreCase))
                return line[prefix.Length..].Trim();
            if (compact != null && line.StartsWith(compact, StringComparison.OrdinalIgnoreCase))
                return line[compact.Length..].Trim();
        }
        return "";
    }

    private static string Rx(string input, string pattern)
    {
        var m = Regex.Match(input, pattern);
        return m.Success ? m.Groups[1].Value : "";
    }

    private static string Md5(string input) =>
        Convert.ToHexStringLower(MD5.HashData(Encoding.UTF8.GetBytes(input)));

    private string GetOfferSdp() =>
        $"v=0\r\no=- 0 0 IN IP4 {_localIp}\r\ns=call\r\nc=IN IP4 {_localIp}\r\nt=0 0\r\n" +
        "m=audio 40000 RTP/AVP 0 8 101\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\n" +
        "a=rtpmap:101 telephone-event/8000\r\na=sendrecv\r\n";

    private static (string Ip, int Port, string Codec)? ParseMediaFromSdp(string sipMessage)
    {
        var lines = sipMessage.Split("\r\n");
        var bodyStart = Array.IndexOf(lines, "");
        if (bodyStart < 0) return null;

        var body = string.Join("\r\n", lines.Skip(bodyStart + 1));
        var sdpLines = body.Split("\r\n");

        var connectionIp = "";
        var audioPort = 0;
        var codec = "PCMU";

        foreach (var line in sdpLines)
        {
            if (line.StartsWith("c=IN IP4 "))
            {
                var parts = line["c=IN IP4 ".Length..].Split(' ');
                connectionIp = parts[0];
            }
            else if (line.StartsWith("m=audio "))
            {
                var parts = line["m=audio ".Length..].Split(' ');
                if (parts.Length > 0 && int.TryParse(parts[0], out var port))
                    audioPort = port;
                if (parts.Contains("0"))
                    codec = "PCMU";
                else if (parts.Contains("8"))
                    codec = "PCMA";
            }
        }

        if (string.IsNullOrEmpty(connectionIp) || audioPort == 0) return null;
        return (connectionIp, audioPort, codec);
    }
}
