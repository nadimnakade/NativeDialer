# Network Connectivity Feature Design

## Problem

The app currently has no global network monitoring. When internet is unavailable:
- API calls fail silently or show cryptic errors
- The dialer remains accessible but calls fail
- Users have no clear indication of connectivity status

## Requirements

| Requirement | Decision |
|-------------|----------|
| Scope | Block dialer entirely when offline |
| UI | Full-screen overlay covering all screens |
| Recovery | Auto-resume silently when connectivity returns |
| Network blocking | Block all network calls (API + WebSocket) |
| Detection method | Android ConnectivityManager callbacks |

## Design

### 1. NetworkMonitor (Domain Layer)

**Files:**
- `common/network/NetworkMonitor.kt` — interface
- `common/network/NetworkMonitorImpl.kt` — implementation

**Interface:**
```kotlin
interface NetworkMonitor {
    val isConnected: StateFlow<Boolean>
}
```

**Implementation:**
- Uses `ConnectivityManager.registerDefaultNetworkCallback()`
- Tracks `onAvailable()` → `true`, `onLost()` → `false`, `onCapabilitiesChanged()` → verify internet capability
- Initial state checked via `activeNetwork` on creation
- Hilt `@Singleton` provided via `@Binds` in `NetworkModule`

### 2. NoInternetOverlay Composable

**File:** `presentation/common/components/NoInternetOverlay.kt`

**Design:**
- Full-screen overlay with white background
- Centered wifi-off icon
- "No Internet Connection" title
- "Please check your network settings" subtitle
- No retry button (auto-resumes)
- Animated fade-in/fade-out using `AnimatedVisibility`
- Uses Material 3 typography and colors

### 3. MainActivity Integration

**File:** `presentation/MainActivity.kt`

**Changes:**
- Inject `NetworkMonitor` via Hilt
- Wrap `NavHost` in a `Box` with `NetworkGate` composable
- When `isConnected` is `false`, show `NoInternetOverlay` above `NavHost`
- When `isConnected` returns `true`, overlay auto-dismisses

**Pseudocode:**
```kotlin
@Composable
fun NetworkGate(monitor: NetworkMonitor) {
    val isConnected by monitor.isConnected.collectAsStateWithLifecycle()
    Box(Modifier.fillMaxSize()) {
        NavHost(...) // existing content
        AnimatedVisibility(visible = !isConnected) {
            NoInternetOverlay()
        }
    }
}
```

### 4. Hilt Module Changes

**File:** `di/NetworkModule.kt`

- Add `@Binds` for `NetworkMonitor` → `NetworkMonitorImpl`
- `NetworkMonitorImpl` gets `ConnectivityManager` via constructor injection

## Files Changed

| File | Action |
|------|--------|
| `common/network/NetworkMonitor.kt` | New — interface |
| `common/network/NetworkMonitorImpl.kt` | New — implementation |
| `presentation/common/components/NoInternetOverlay.kt` | New — composable |
| `presentation/MainActivity.kt` | Modify — add NetworkGate wrapper |
| `di/NetworkModule.kt` | Modify — add @Binds for NetworkMonitor |

## What Does NOT Change

- No ViewModel changes
- No repository changes
- No API/interface changes
- No existing screen composables
- No existing connectivity handling in PjsipTelephonyProvider (SIP-specific, separate concern)

## Testing

1. Turn off WiFi/mobile data → overlay appears
2. Turn on WiFi/mobile data → overlay disappears, app resumes from current screen
3. During active call, kill network → overlay appears, call should already be handled by PjsipTelephonyProvider
4. App startup with no network → overlay shown immediately
5. App startup with network → normal flow, overlay never shown
