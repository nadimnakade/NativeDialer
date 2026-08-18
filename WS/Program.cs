using System.Net;
using System.Net.Sockets;
using System.Net.WebSockets;
using Microsoft.AspNetCore.Mvc;
using WsClient;
using WsClient.Data;
using WsClient.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.Configure<SipConfig>(builder.Configuration.GetSection("SipConfig"));
builder.Services.AddSingleton<LogBuffer>();
builder.Services.AddSingleton<SipWorkerService>();
builder.Services.AddHostedService(sp => sp.GetRequiredService<SipWorkerService>());
builder.Services.AddControllers();
builder.Services.AddCors(o => o.AddDefaultPolicy(p => p.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader()));

var connString = builder.Configuration.GetConnectionString("DefaultConnection") ?? "";
builder.Services.AddSingleton(new DbConnectionFactory(connString));
builder.Services.AddSingleton(new DialerHelper(connString));
builder.Services.AddSingleton<CallSessionManager>();
builder.Services.AddSingleton<ISseBroadcaster, SseBroadcaster>();
builder.Services.AddSingleton<RtpAudioService>();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("v1", new() { Title = "SIP WebSocket Client", Version = "v1" });
});

var app = builder.Build();

app.UsePathBase("/Dialer_WS");
app.UseRouting();
app.UseCors();
app.UseWebSockets(new WebSocketOptions { AllowedOrigins = { "*" } });
app.MapControllers();

var rtpService = app.Services.GetRequiredService<RtpAudioService>();
try
{
    rtpService.Start();
}
catch (Exception ex)
{
    var logger = app.Services.GetRequiredService<ILogger<Program>>();
    logger.LogError(ex, "RTP audio service failed to start on UDP port 40000 — calls will work but audio relay is disabled");
}

app.UseSwagger();
app.UseSwaggerUI(c =>
{
    c.SwaggerEndpoint("/Dialer_WS/swagger/v1/swagger.json", "SIP WebSocket Client V1");
    c.RoutePrefix = "swagger";
});

// Simple status for monitoring
app.MapGet("/health", (SipWorkerService worker) => Results.Ok(new
{
    status = worker.Client.IsRegistered ? "registered" : "connecting",
    server = $"{builder.Configuration["SipConfig:Server"]}:{builder.Configuration["SipConfig:Port"]}",
    last_error = worker.LastError,
    timestamp = DateTime.UtcNow
})).WithName("Health").WithDescription("Registration status");

// Plain text logs
app.MapGet("/logs", (LogBuffer log) => Results.Ok(new { entries = log.GetRecent(300) }))
    .WithName("Logs").WithDescription("Plain text SIP logs");

// Structured SIP events for support team
app.MapGet("/api/sip-flow", (LogBuffer log) =>
{
    var events = log.GetSipEvents();
    var flow = events.Select(e => new
    {
        time = e.Timestamp.ToString("HH:mm:ss.fff"),
        direction = e.Direction,
        method = e.StartLine,
        headers = e.Headers,
        body = string.IsNullOrWhiteSpace(e.Body) ? null : e.Body
    });
    return Results.Ok(new { count = flow.Count(), events = flow });
}).WithName("SipFlow").WithDescription("Structured SIP request/response events with parsed headers");

// Full SIP message dump
app.MapGet("/api/sip-messages", (LogBuffer log) =>
{
    var events = log.GetSipEvents();
    var messages = events.Select(e => new
    {
        timestamp = e.Timestamp.ToString("o"),
        direction = e.Direction,
        raw = $"{e.StartLine}\r\n" + string.Join("\r\n", e.Headers.Select(h => $"{h.Key}: {h.Value}")) +
              (string.IsNullOrWhiteSpace(e.Body) ? "" : $"\r\n\r\n{e.Body}")
    });
    return Results.Ok(messages);
}).WithName("SipMessages").WithDescription("Full raw SIP messages");

// Config dump (no password)
app.MapGet("/api/config", (IConfiguration config) => Results.Ok(new
{
    server = config["SipConfig:Server"],
    port = config["SipConfig:Port"],
    username = config["SipConfig:Username"],
    realm = config["SipConfig:Realm"],
    userAgent = config["SipConfig:UserAgent"],
    useTls = config["SipConfig:UseTls"],
    wsUrl = $"wss://{config["SipConfig:Server"]}:{config["SipConfig:Port"]}/",
    registerUri = $"sip:{config["SipConfig:Server"]}"
})).WithName("Config").WithDescription("Connection configuration (password excluded)");

// Manual registration with custom credentials
app.MapPost("/api/register", async (SipWorkerService worker, [FromQuery] string? username, [FromQuery] string? password, [FromBody] RegisterRequest? body) =>
{
    username = username ?? body?.Username;
    password = password ?? body?.Password;

    if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(password))
        return Results.BadRequest(new { error = "username and password are required (query or JSON body)" });

    var cfg = worker.GetConfig();
    var client = new SipWebSocketClient(
        cfg.Server, cfg.Port, username, password, cfg.Realm,
        cfg.UserAgent, cfg.UseTls);

    try
    {
        using var cts = new CancellationTokenSource(TimeSpan.FromSeconds(30));
        var events = await client.RegisterOnceAsync(username, password, cts.Token);

        var flowData = new
        {
            username,
            server = $"{cfg.Server}:{cfg.Port}",
            registerUri = $"sip:{cfg.Server}",
            events = events.Select(e => new
            {
                timestamp = e.Timestamp.ToString("HH:mm:ss.fff"),
                direction = e.Direction,
                startLine = e.StartLine,
                headers = e.Headers,
                body = string.IsNullOrWhiteSpace(e.Body) ? null : e.Body,
                raw = e.Direction == "sent"
                    ? $"{e.StartLine}\r\n" + string.Join("\r\n", e.Headers.Select(h => $"{h.Key}: {h.Value}")) + (string.IsNullOrWhiteSpace(e.Body) ? "" : $"\r\n\r\n{e.Body}")
                    : null
            })
        };

        LastFlow.Save(flowData);
        return Results.Ok(flowData);
    }
    catch (OperationCanceledException)
    {
        return Results.Problem("Registration timed out after 30s. Server did not respond.");
    }
    catch (WebSocketException wex)
    {
        return Results.Problem($"WebSocket error: {wex.Message}");
    }
    catch (Exception ex)
    {
        return Results.Problem($"Registration failed: {ex.Message}");
    }
}).WithName("Register").WithDescription("Trigger a one-shot SIP REGISTER with custom username/password");

// Diagnostics
app.MapGet("/diag", async (SipWorkerService worker, IConfiguration config) =>
{
    var server = config["SipConfig:Server"] ?? "";
    var results = new List<object>();

    try
    {
        var addresses = await Dns.GetHostAddressesAsync(server);
        results.Add(new { test = "dns", host = server, ips = addresses.Select(a => a.ToString()).ToArray(), success = true });
    }
    catch (Exception ex)
    {
        results.Add(new { test = "dns", host = server, error = ex.Message, success = false });
    }

    results.Add(new { test = "ws_status", registered = worker.Client.IsRegistered, last_error = worker.LastError });

    return Results.Ok(results);
}).WithName("Diagnostics").WithDescription("DNS and WebSocket connection diagnostics");

// Last registration flow
app.MapGet("/api/last-flow", () =>
{
    var (data, timestamp) = LastFlow.Get();
    if (data == null)
        return Results.Ok(new { status = "No registration attempted yet" });
    return Results.Ok(new { timestamp = timestamp.ToString("o"), flow = data });
}).WithName("LastFlow").WithDescription("Returns the last SIP registration flow from /api/register");

// Place a call
app.MapPost("/api/place", async (SipWorkerService worker, [FromBody] PlaceRequest body) =>
{
    if (string.IsNullOrEmpty(body.PhoneNumber))
        return Results.BadRequest(new { error = "phone_number is required" });

    if (!worker.Client.IsRegistered)
        return Results.BadRequest(new { error = "Not registered with SIP server" });

    if (worker.Client.InCall)
        return Results.BadRequest(new { error = "Already in a call", callee = worker.Client.ActiveCallee });

    try
    {
        await worker.Client.SendInviteAsync(body.PhoneNumber);
        return Results.Ok(new
        {
            success = true,
            call_id = worker.Client.ActiveCallId,
            callee = body.PhoneNumber,
            message = "INVITE sent"
        });
    }
    catch (Exception ex)
    {
        return Results.Problem($"Failed to place call: {ex.Message}");
    }
}).WithName("Place").WithDescription("Place a SIP call to a phone number");

// Hangup a call
app.MapPost("/api/hangup", async (SipWorkerService worker) =>
{
    if (!worker.Client.InCall)
        return Results.BadRequest(new { error = "No active call" });

    try
    {
        await worker.Client.SendByeAsync(worker.Client.ActiveCallee, worker.Client.ActiveCallId);
        return Results.Ok(new { success = true, message = "BYE sent" });
    }
    catch (Exception ex)
    {
        return Results.Problem($"Failed to hangup: {ex.Message}");
    }
}).WithName("Hangup").WithDescription("Hangup the active call");

// Call status
app.MapGet("/api/call-status", (SipWorkerService worker) => Results.Ok(new
{
    registered = worker.Client.IsRegistered,
    in_call = worker.Client.InCall,
    callee = worker.Client.ActiveCallee,
    call_id = worker.Client.ActiveCallId
})).WithName("CallStatus").WithDescription("Current call state");

// Dialpad page
app.MapGet("/dialpad", () => Results.Content(DialpadHtml.Value, "text/html")).WithName("Dialpad").WithDescription("Web dialpad UI");

app.Run();

public record RegisterRequest(string Username, string Password);
public record PlaceRequest(string PhoneNumber);

public static class LastFlow
{
    private static readonly object _lock = new();
    private static object? _data;
    private static DateTime _timestamp;

    public static void Save(object data)
    {
        lock (_lock) { _data = data; _timestamp = DateTime.UtcNow; }
    }

    public static (object? data, DateTime timestamp) Get()
    {
        lock (_lock) { return (_data, _timestamp); }
    }
}

public static class DialpadHtml
{
    public static string Value => @"
<!DOCTYPE html>
<html lang=""en"">
<head>
<meta charset=""UTF-8"">
<meta name=""viewport"" content=""width=device-width, initial-scale=1.0"">
<title>SIP Dialpad</title>
<style>
  * { box-sizing: border-box; margin: 0; padding: 0; }
  body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #1a1a2e; color: #eee; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
  .phone { background: #16213e; border-radius: 24px; padding: 32px; width: 340px; box-shadow: 0 20px 60px rgba(0,0,0,0.5); }
  .status-bar { text-align: center; margin-bottom: 20px; font-size: 13px; color: #888; }
  .status-bar .dot { display: inline-block; width: 8px; height: 8px; border-radius: 50%; margin-right: 6px; }
  .status-bar .dot.green { background: #4ade80; }
  .status-bar .dot.red { background: #f87171; }
  .status-bar .dot.yellow { background: #fbbf24; }
  .display { background: #0f3460; border-radius: 12px; padding: 16px 20px; margin-bottom: 20px; text-align: right; min-height: 56px; display: flex; align-items: center; justify-content: flex-end; }
  .display input { background: none; border: none; color: #fff; font-size: 28px; font-weight: 300; text-align: right; width: 100%; outline: none; letter-spacing: 2px; }
  .keypad { display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px; margin-bottom: 24px; }
  .key { background: #1a1a2e; border: none; border-radius: 12px; color: #fff; font-size: 24px; font-weight: 300; height: 60px; cursor: pointer; transition: background 0.15s; }
  .key:hover { background: #0f3460; }
  .key:active { background: #e94560; transform: scale(0.95); }
  .key.sub { font-size: 11px; color: #666; letter-spacing: 3px; }
  .actions { display: flex; gap: 12px; }
  .btn { flex: 1; border: none; border-radius: 12px; padding: 14px; font-size: 15px; font-weight: 600; cursor: pointer; transition: all 0.15s; }
  .btn-call { background: #4ade80; color: #000; }
  .btn-call:hover { background: #22c55e; }
  .btn-call:disabled { background: #333; color: #666; cursor: not-allowed; }
  .btn-hangup { background: #e94560; color: #fff; }
  .btn-hangup:hover { background: #dc2626; }
  .btn-hangup:disabled { background: #333; color: #666; cursor: not-allowed; }
  .log { margin-top: 16px; background: #0a0a1a; border-radius: 8px; padding: 12px; max-height: 160px; overflow-y: auto; font-family: monospace; font-size: 11px; color: #888; line-height: 1.6; }
  .log .sent { color: #4ade80; }
  .log .recv { color: #60a5fa; }
  .log .err { color: #f87171; }
</style>
</head>
<body>
<div class=""phone"">
  <div class=""status-bar"" id=""statusBar""><span class=""dot red""></span>Connecting...</div>
  <div class=""display""><input type=""tel"" id=""number"" placeholder=""Enter number"" autofocus /></div>
  <div class=""keypad"">
    <button class=""key"" onclick=""press('1')"">1</button>
    <button class=""key"" onclick=""press('2')"">2<span class=""sub"">ABC</span></button>
    <button class=""key"" onclick=""press('3')"">3<span class=""sub"">DEF</span></button>
    <button class=""key"" onclick=""press('4')"">4<span class=""sub"">GHI</span></button>
    <button class=""key"" onclick=""press('5')"">5<span class=""sub"">JKL</span></button>
    <button class=""key"" onclick=""press('6')"">6<span class=""sub"">MNO</span></button>
    <button class=""key"" onclick=""press('7')"">7<span class=""sub"">PQRS</span></button>
    <button class=""key"" onclick=""press('8')"">8<span class=""sub"">TUV</span></button>
    <button class=""key"" onclick=""press('9')"">9<span class=""sub"">WXYZ</span></button>
    <button class=""key"" onclick=""press('*')"">*</button>
    <button class=""key"" onclick=""press('0')"">0<span class=""sub"">+</span></button>
    <button class=""key"" onclick=""press('#')"">#</button>
  </div>
  <div class=""actions"">
    <button class=""btn btn-call"" id=""btnCall"" onclick=""placeCall()"">Call</button>
    <button class=""btn btn-hangup"" id=""btnHangup"" onclick=""hangup()"" disabled>Hang Up</button>
  </div>
  <div class=""log"" id=""log""></div>
</div>
<script>
  const BASE = window.location.pathname.replace(/\/dialpad$/, '/');
  const num = document.getElementById('number');
  const btnCall = document.getElementById('btnCall');
  const btnHangup = document.getElementById('btnHangup');
  const statusBar = document.getElementById('statusBar');
  const logEl = document.getElementById('log');

  function press(d) { num.value += d; num.focus(); }
  function addLog(msg, cls) {
    const t = new Date().toLocaleTimeString();
    logEl.innerHTML += '<div class=""' + cls + '"">' + t + ' ' + msg + '</div>';
    logEl.scrollTop = logEl.scrollHeight;
  }

  async function checkStatus() {
    try {
      const r = await fetch(BASE + 'api/call-status');
      const d = await r.json();
      if (d.registered) {
        statusBar.innerHTML = '<span class=""dot green""></span>Registered' + (d.in_call ? ' — On Call: ' + d.callee : '');
        btnCall.disabled = d.in_call;
        btnHangup.disabled = !d.in_call;
      } else {
        statusBar.innerHTML = '<span class=""dot yellow""></span>Connecting...';
        btnCall.disabled = true;
        btnHangup.disabled = true;
      }
    } catch {
      statusBar.innerHTML = '<span class=""dot red""></span>Offline';
      btnCall.disabled = true;
      btnHangup.disabled = true;
    }
  }

  async function placeCall() {
    const phone = num.value.trim();
    if (!phone) return;
    btnCall.disabled = true;
    addLog('Placing call to ' + phone + '...', 'sent');
    try {
      const r = await fetch(BASE + 'api/place', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ phoneNumber: phone })
      });
      const d = await r.json();
      if (d.success) {
        addLog('Call placed — ID: ' + d.call_id, 'sent');
        btnHangup.disabled = false;
      } else {
        addLog('Error: ' + (d.error || d.title || 'Unknown'), 'err');
        btnCall.disabled = false;
      }
    } catch (e) {
      addLog('Request failed: ' + e.message, 'err');
      btnCall.disabled = false;
    }
  }

  async function hangup() {
    btnHangup.disabled = true;
    addLog('Hanging up...', 'sent');
    try {
      const r = await fetch(BASE + 'api/hangup', { method: 'POST' });
      const d = await r.json();
      if (d.success) {
        addLog('Call ended', 'sent');
        btnCall.disabled = false;
      } else {
        addLog('Error: ' + (d.error || d.title || 'Unknown'), 'err');
        btnHangup.disabled = false;
      }
    } catch (e) {
      addLog('Request failed: ' + e.message, 'err');
      btnHangup.disabled = false;
    }
  }

  num.addEventListener('keydown', e => { if (e.key === 'Enter') placeCall(); });
  checkStatus();
  setInterval(checkStatus, 3000);
</script>
</body>
</html>";
}
