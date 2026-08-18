# Dialer Backend — Status & Architecture

## Overview
Android SIP dialer app routing calls through a .NET backend. All APIs consolidated into **WsClient** (`D:\Git_Repos\Dialer\WS`), deployed at `https://1up.co.in/Dialer_WS/`. The separate Backend project has been retired.

---

## Infrastructure

| Component | Details |
|-----------|---------|
| SIP Server | `sip2.cloud-connect.in:7443` (WSS), FreeSWITCH + OpenSIPS proxy |
| Backend | WsClient — ASP.NET Core 9, self-contained `win-x64` |
| Server | Windows Server `160.250.28.172`, IIS, path `H:\LIVE_PROJECTS\Dialer_WS` |
| Android | `ApiConstants.kt` → `BASE_URL = https://1up.co.in/Dialer_WS/` |
| SIP Credentials | `102724701` / `Tejas@001` / realm `sip2.cloud-connect.in` / UA `PJSUA` |

---

## SIP Flow

```
Android → WsClient (IIS) → SIP Server (WSS)
                ↓
        REGISTER → 407 → REGISTER+auth → 200 OK → keepalive (\r\n\r\n)
        INVITE   → 407 → INVITE+auth  → 100/180/200 → ACK → BYE
```

### What works
- ✅ WebSocket connect to `sip2.cloud-connect.in:7443` (WSS)
- ✅ SIP REGISTER → 401 → REGISTER with auth → 200 OK
- ✅ CRLF keepalive every 20s after registration
- ✅ SIP INVITE → 407 → Auth INVITE → 183 Session Progress → PRACK → 200 OK
- ✅ BYE with correct INVITE tag and Contact header
- ✅ ACK for all error responses (4xx/5xx/6xx)
- ✅ Compact SIP header support (i:, t:, f:, v:)
- ✅ SSE endpoint (`GET /Call/events`) streaming real-time call state to Android
- ✅ Android BackendTelephonyProvider consumes SSE for live UI updates
- ✅ IIS health endpoint confirms new code loaded
- ✅ Android emulator + TestCallActivity working

### What was broken (fixed in latest builds)
- ❌ Re-INVITE after 407 had **empty Call-ID** — compact `i:` header not recognized
- ❌ Re-INVITE had **wrong From tag** — was generating new tag instead of reusing original
- ❌ **Proxy-Authorization had empty realm/nonce** — `??` operator never reached `Proxy-Authenticate:` fallback
- ❌ ACK used REGISTER tag instead of INVITE tag
- ❌ BYE had wrong Contact header (destination instead of source)
- ❌ **SDP `m=audio 0`** — port 0 = "hold", causing 488 Not Acceptable Here
- ❌ **Missing PRACK** — 183 with `Require: 100rel` needed PRACK acknowledgement
- ❌ **To tag in re-INVITE** — confused OpenSIPS routing, causing 404
- ❌ **No real-time events to Android** — SIP events (ringing, failed) never reached the app

---

## Bug Fixes (Latest Build)

### 1. Compact SIP Headers
Server sends `i:`, `t:`, `f:`, `v:` instead of full header names.

```csharp
// Before: only matched full names
ExtractHeader(lines, "Call-ID:")  // returns "" when server sends "i:"

// After: also checks compact forms
var compact = prefix switch {
    "Call-ID:" => "i:",
    "From:"    => "f:",
    "To:"      => "t:",
    "Via:"     => "v:",
    _ => null
};
```

### 2. Auth Header Extraction
`ExtractHeader` returns `""` (not `null`), so `??` never falls through.

```csharp
// Before: never reached Proxy-Authenticate
var auth = ExtractHeader(lines, "WWW-Authenticate:")
           ?? ExtractHeader(lines, "Proxy-Authenticate:");
// auth = "" because "" is not null

// After:
var auth = ExtractHeader(lines, "WWW-Authenticate:");
if (string.IsNullOrEmpty(auth))
    auth = ExtractHeader(lines, "Proxy-Authenticate:");
```

### 3. INVITE Tag & Call-ID Reuse
Re-INVITE must preserve the original From tag and Call-ID.

```csharp
// Before: generated new values
var fromTag = $"tag{Rng.Next(...)}";       // new tag
var inviteCallId = callId ?? Guid.NewGuid(); // "" ?? new = new

// After: reuse originals
var fromTag = !string.IsNullOrEmpty(_inviteTag) ? _inviteTag : ...;
var inviteCallId = !string.IsNullOrEmpty(callId) ? callId : _activeCallId;
```

New field `_inviteTag` stores the INVITE's From tag for reuse in auth re-INVITE and ACK.

---

## File Structure

```
D:\Git_Repos\Dialer\WS\
├── SipWebSocketClient.cs    ← SIP over WebSocket (core)
├── SipWorkerService.cs      ← BackgroundService with auto-reconnect
├── CallSessionManager.cs    ← Call session tracking
├── Controllers/
│   └── CallController.cs    ← [Route("Call")], PlaceCall action
├── Program.cs               ← ASP.NET Core host
├── WsClient.csproj          ← Dapper + Microsoft.Data.SqlClient
├── appsettings.json         ← Kestrel http://localhost:5031
├── appsettings.Production.json
└── publish\                 ← self-contained deploy folder → copy to server
```

---

## Deployment

```bash
# Build
dotnet publish WsClient.csproj -c Release -r win-x64 --self-contained -o publish

# Deploy via RDP
# Copy publish\* → H:\LIVE_PROJECTS\Dialer_WS on server
# Restart IIS app pool
```

---

## Key Methods

| Method | Purpose |
|--------|---------|
| `ConnectAsync` | WSS connect → REGISTER → receive loop |
| `SendInviteAsync` | First INVITE (no auth) |
| `SendInviteWithAuthAsync` | Re-INVITE with Proxy-Authorization after 407 |
| `SendRegisterAsync` | Initial REGISTER |
| `SendRegisterWithAuthAsync` | REGISTER with Authorization after 401/407 |
| `SendAckAsync` | ACK after 200 OK to INVITE |
| `SendPrackAsync` | PRACK for reliable provisional responses (183) |
| `SendByeAsync` | BYE to end call |
| `KeepaliveLoopAsync` | CRLF keepalive every 20s |
| `HandleMessage` | SIP response router |
| `ExtractHeader` | Header extraction with compact form support |

---

## SSE Events (Server → Android)

Endpoint: `GET /Call/events?userKey=xxx`

| Event | State | Description |
|-------|-------|-------------|
| `ringing` | `CallState.Ringing` | 180/183 received from FreeSWITCH |
| `connected` | `CallState.Connected` | 200 OK received (call answered) |
| `failed` | `CallState.Error` | 4xx/5xx/6xx error received |
| `ended` | `CallState.Idle` | BYE completed |

Android uses OkHttp SSE (`EventSourceListener`) to consume events and update UI in real-time.

---

## Android Integration

- `TestCallActivity.kt` — test dialpad, launches call via backend
- `BackendTelephonyProvider.kt` — Android backend telephony implementation
- `ApiConstants.kt` — `BASE_URL = https://1up.co.in/Dialer_WS/`
- `network_security_config.xml` — cleartext blocked by default
- `AndroidManifest.xml` — `TestCallActivity` registered as LAUNCHER

---

## Pending / Known Issues

- SIP server accumulates stale registrations from repeated reconnects (asked Cloud-Connect to clear)
- 480 "Temporarily Unavailable" on outbound calls — Cloud-Connect routing issue, not our code
- Need to verify correct number format with Cloud-Connect (country code, prefixes)
- Server: `H:\LIVE_PROJECTS\Dialer_WS` on `160.250.28.172`
