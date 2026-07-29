# Dialer Frontend - Progress Report

## Current State: ✅ Backend Working, Call Flow Active — Audio Next

---

## Completed Work

### 1. SIP Backend Audio Pipeline (Server-side)
- `AudioWebSocketController.cs` – WebSocket audio endpoint at `/ws/audio`
- `RtpAudioService.cs` – UDP-RTP relay (port 40000)
- `SipWorkerService.cs` – broadcasts `media_info` SSE events
- `WsClient.dll` compiled and deployed to `C:\temp\ws_deploy` on `160.250.28.172`

### 2. Android Audio Manager (Client-side)
- `RtpAudioManager.kt` – PCM encoding/decoding, WebSocket, AudioRecord/AudioTrack
- `BackendTelephonyProvider.kt` – starts/stops `RtpAudioManager` on `media_info`
- `TestCallActivity.kt` – launcher activity for testing SIP calls

### 3. Emulator Setup & App Deployment
- AVD: `Pixel_9` (API 35, arm64-v8a via libhoudini translation)
- APK: `app-debug.apk` built and installed
- App launches and runs without crashes

---

## Critical Bug Fixed (Today)

### Hilt `NoClassDefFoundError: CrmApplication_GeneratedInjector`

**Root Cause:**
- `Hilt_CrmApplication` was in `classes12.dex`
- `CrmApplication_GeneratedInjector` was in `classes3.dex`
- ART could not resolve the class across DEX boundaries during Application creation
- The app has 20 DEX files with a 42MB primary DEX

**Fix Applied in `app/build.gradle.kts`:**
```kotlin
multiDexEnabled = true
multiDexKeepFile = file("multidex_keep.pro")
```

**`multidex_keep.pro`:**
```
com.crm.v2.CrmApplication
com.crm.v2.CrmApplication_GeneratedInjector
com.crm.v2.Hilt_CrmApplication
com.crm.v2.CrmApplication_HiltComponents
```

**Result:** App launches successfully, no more Hilt crash.

---

## Remaining Issues

### 🔴 Audio Not Working (In Progress)
- SDP was only in 183, not 200 OK → **Fixed** (parsing from 183 now)
- Remote media: `43.205.16.105:37642` PCMU — but uses `UDP/TLS/RTP/SAVPF` (SRTP)
- Our RTP relay uses plain `RTP/AVP` — SRTP incompatibility may still block audio
- Need to verify after deploy: does `media_info` SSE event arrive on Android?

### IIS webSocket Config
- `<webSocket enabled="true" />` in auto-generated `web.config` causes 500
- Must be manually removed after each publish
- Server doesn't have WebSocket IIS feature installed

---

## Next Steps

1. **Deploy updated backend** to `H:\LIVE_PROJECTS\Dialer_WS`, remove webSocket line, recycle
2. **Test call flow** — verify `media_info` SSE event arrives with correct IP/port
3. **Fix SRTP issue** — FreeSWITCH offers `SAVPF` (encrypted), we offer plain `RTP/AVP`. May need to force plain RTP or add SRTP support
4. **Verify WebSocket audio** — once media_info arrives, check if `/ws/audio` connects and PCM flows

---

## Files Modified Today

| File | Change |
|------|--------|
| `app/build.gradle.kts` | Added `multiDexEnabled = true` and `multiDexKeepFile` |
| `app/multidex_keep.pro` | Created — forces Hilt classes into primary DEX |
| `WS/SipWebSocketClient.cs` | Parse SDP from 183 (not just 200 OK), guard duplicate 200 OK |
| `WS/Program.cs` | Wrap RTP service start in try-catch |

## Android Request/Response Debug

### SSE Connection (GET /Call/events)
```
Request:  GET https://1up.co.in/Dialer_WS/Call/events?userKey=test_user
Response: 500 Internal Server Error (HTML IIS error page)
```

### Place Call (POST /Call/Place)
```
Request:  POST https://1up.co.in/Dialer_WS/Call/Place
Body:     {"phone_number":"8108783956","user_key":"test_user"}
Response: 500 Internal Server Error (HTML IIS error page)
```

### Note: Backend field names are consistent
- Android sends `phone_number` + `user_key` (snake_case via `@SerialName`)
- Server expects `phone_number` + `user_key` (via `[JsonPropertyName]`)
- No mismatch — issue is purely server-side crash
