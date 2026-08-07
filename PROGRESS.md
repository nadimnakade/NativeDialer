# CRM Dialer — Progress Log

## App Overview
Production CRM Dialer Android app (Kotlin + Jetpack Compose) with 3 tabs (Auto / Lead / Contact), PJSIP-based calling, auto-dialer queue, call state display, and status update flow.

## Two Base URLs
- **REST API**: `https://1up.co.in/DialerAPI/api/` — login, pickup, lead, dialer data
- **PJSIP Server**: `sip2.cloud-connect.in:7065` (user `102724701`, pass `Tejas@001`)

## minSdk
- **26** (Android 8.0+). PJSIP native libraries are pre-built for API 26+.
- Android 6-7 (API 23-25) would require rebuilding the entire PJSIP stack from source.

---

## Fixed Bugs

### 1. PJSIP crash on Start Dialing (native SIGSEGV)
- **Root cause**: `TelephonyConfig()` was created with all empty defaults (no SIP server/user/pass), causing a null pointer dereference at offset `0x58` in PJSIP native code.
- **Fix**: Pass `ApiConstants.Sip` values explicitly to `TelephonyConfig()` in `startAutoDial()` and `manualCall()`.

### 2. PJSIP race condition causing double-init crash
- **Root cause**: `registerNetworkCallback()` was called at the end of `register()`. The network callback fired immediately on a background thread, triggering `reRegister()` which destroyed the PJSIP stack before the SIP registration callback could set `_registrationState = true`.
- **Fix**:
  - Moved `registerNetworkCallback()` to the provider's `init` block (runs once).
  - Added `@Volatile` to `isInitialized`.
  - Added `isReRegistering` guard to prevent concurrent re-registration.

### 3. Busy/reject/hangup not handled
- **Root cause**: PJSIP call state mapping was wrong. States 2-7 were used instead of 1-6. State 6 = DISCONNECTED was being mapped to Connected.
- **Fix**: Corrected the `onCallState` mapping in `PjsipTelephonyProvider`:
  - 1 → Calling
  - 2, 3 → Ringing
  - 4, 5 → Connected
  - 6 → Idle (disconnected)

### 4. Mute not working
- **Root cause**: Was just a log statement.
- **Fix**: Added `nativeSetMute` JNI function using `pjsua_conf_adjust_tx_level`. Mute state tracked in ViewModel and UI.

### 5. Speaker not working
- **Root cause**: Was just a log statement.
- **Fix**: Uses `AudioManager.setSpeakerphoneOn()` + `MODE_IN_COMMUNICATION`. Resets on hangup via `resetAudioState()`.

### 6. Hangup not canceling call midway
- **Root cause**: `hangup()` force-set `_callState.value = Idle` immediately, racing with the PJSIP native callback.
- **Fix**: `hangup()` only sends the PJSIP hangup command and resets audio. State transition comes naturally from the PJSIP callback (state 6). Added `nativeGetActiveCallId` JNI fallback using `pjsua_enum_calls` if `currentCallId` was lost.

### 7. Double delay in auto-dial next call
- **Root cause**: `submitStatus` had `delay(5000L)` then called `autoDialNext()` which also had `delay(autoDialInterval * 1000L)` = 10s total.
- **Fix**: Removed the delay from `submitStatus`. `autoDialNext()` handles the single 5s delay.

### 8. `autoDialNext` always restarted from index 0
- **Root cause**: Used `currentLead?.key` to find the index, but `submitStatus` had already set `currentLead = null`.
- **Fix**: Added `lastCalledIndex` field tracked in `callLead()`. `autoDialNext()` uses `lastCalledIndex + 1`.

### 9. Update Status API not firing
- **Root cause**: Sending as JSON body; Ionic sends as `application/x-www-form-urlencoded`.
- **Fix**: Changed `@Body` to `@FieldMap` + `@FormUrlEncoded` in `PickupApi.updateDialer`. Added all missing fields (CallBackTime, CallDuration, CallDate, IsHLLAPBL, IsCibilBad, IsRealEstate).

### 10. `manualCall` loading state never cleared on success
- **Root cause**: `isLoading` was only cleared on exception.
- **Fix**: Cleared `isLoading` immediately after `telephonyManager.call()` returns.

### 11. No error feedback to user
- **Root cause**: Errors were stored in state but never shown.
- **Fix**: Added `SnackbarHost` via `Scaffold` in `DialerScreen` with `LaunchedEffect` on `uiState.error`.

### 12. `startAutoDial` coroutine not cancellable
- **Root cause**: Coroutine wasn't assigned to `autoDialJob`.
- **Fix**: `startAutoDial` now assigns to `autoDialJob` so `stopAutoDial` can cancel it.

### 13. `updateDialingStatus` not called on UpdateStatus page load
- **Root cause**: Missing — Ionic app calls `Pickup/UpdateDialingStatus` with the lead key in `initData()`.
- **Fix**: When call ends, ViewModel now calls `dialerRepository.updateDialingStatus(lead.key)` before showing UpdateStatusScreen.

---

## Features Implemented

### 3-Tab Dialer UI
- **Auto tab**: Queue list, Start/Stop dialer, Call button per lead
- **Lead tab**: Follow-up leads, pull-to-refresh, load more
- **Contact tab**: Device contacts, search, alphabetical grouping

### Auto-Dialer Flow
- Start → register PJSIP → load queue → call first lead
- ActiveCallScreen (calling/ringing/connected states, live timer, mute/speaker/hold)
- Hangup → UpdateStatusScreen (lead info, call duration, full status form)
- Save → 5s delay → next call

### UpdateStatusScreen
- 9 status options (Follow Up, Same Day Follow Up, Ringing, Not Contactable, etc.)
- Follow-up date/time scheduling
- HL/LAP/BL/PL/CC selection
- Cibil Bad, Real Estate checkboxes
- Add Daily Login fields (LOS No, App No, Banks, Product, Region, Login Amount)
- Remarks + WhatsApp message
- Loading overlay during save

### UI Polish
- Gradient backgrounds, pulse animations, color-coded status badges
- Animated toggle states for Mute/Speaker/Hold buttons
- Rounded cards, first-letter avatars, empty states with icons
- TopAppBar, divider-less tabs with custom indicator
- Loading overlay with animated dots
- Snackbar for error messages
- Shimmer loading items, pulse loading animation

### Audio Control
- Mute via PJSIP `pjsua_conf_adjust_tx_level`
- Speaker via Android `AudioManager.setSpeakerphoneOn`
- Audio state resets on hangup

---

## Build & Install

### Build
```bash
cd D:\Git_Repos\Dialer\Frontend
gradlew.bat assembleDebug
```

### APK Location
`D:\Git_Repos\Dialer\Frontend\app\build\outputs\apk\debug\app-debug.apk`

### Install
```bash
adb -s XCVSXSMVTKMBE6QC install -r D:\Git_Repos\Dialer\Frontend\app\build\outputs\apk\debug\app-debug.apk
```

### Launch
```bash
adb -s XCVSXSMVTKMBE6QC shell am start -n com.crm.v2.debug/com.crm.v2.presentation.MainActivity
```

### Debug Logs
```bash
# PJSIP
adb -s XCVSXSMVTKMBE6QC logcat -s PjsipBridge PjsipManager PjsipTelephonyProvider PjsipLib

# API/Repository
adb -s XCVSXSMVTKMBE6QC logcat -s DialerRepository

# Errors
adb -s XCVSXSMVTKMBE6QC logcat *:E | findstr "crm.v2"
```

---

## Key Files
- `app/src/main/java/com/crm/v2/presentation/ui/dialer/DialerScreen.kt` — 3-tab UI + Snackbar
- `app/src/main/java/com/crm/v2/presentation/ui/dialer/DialerViewModel.kt` — full call flow
- `app/src/main/java/com/crm/v2/presentation/ui/dialer/ActiveCallScreen.kt` — call screen
- `app/src/main/java/com/crm/v2/presentation/ui/dialer/UpdateStatusScreen.kt` — status form
- `app/src/main/java/com/crm/v2/presentation/ui/common/LoadingAnimation.kt` — loading overlay
- `app/src/main/java/com/crm/v2/telephony/PjsipManager.kt` — PJSIP native bridge
- `app/src/main/java/com/crm/v2/telephony/provider/PjsipTelephonyProvider.kt` — telephony provider
- `app/src/main/java/com/crm/v2/telephony/TelephonyState.kt` — call states + config
- `app/src/main/jni/pjsip_jni.c` — native JNI bridge
- `app/src/main/java/com/crm/v2/data/repository/DialerRepositoryImpl.kt` — API calls
- `app/src/main/java/com/crm/v2/data/network/api/PickupApi.kt` — Retrofit API
- `app/src/main/java/com/crm/v2/common/constants/ApiConstants.kt` — URLs + SIP config

---

## Known Limitations
- minSdk 26 (Android 8.0+). Android 6-7 requires PJSIP rebuild.
- PJSIP native lib is `arm64-v8a` only — won't run on x86_64 emulator.
- `AudioManager.isSpeakerphoneOn` is deprecated on API 31+ but still works.
- `Icons.Filled.NavigateNext` is deprecated (use AutoMirrored version).
