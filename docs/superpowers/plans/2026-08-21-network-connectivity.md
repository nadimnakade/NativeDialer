# Network Connectivity Feature Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add global network connectivity monitoring that shows a full-screen overlay when offline and auto-resumes when connectivity returns.

**Architecture:** Single `NetworkMonitor` class using `ConnectivityManager.NetworkCallback` exposing `StateFlow<Boolean>`. A `NoInternetOverlay` composable wraps the `NavHost` in `MainActivity` via a `NetworkGate` composable. No changes to existing ViewModels or screens.

**Tech Stack:** Kotlin, Jetpack Compose, Hilt, Android ConnectivityManager, StateFlow

## Global Constraints

- Min SDK 24 (Android 7.0)
- Hilt DI with `@Singleton` scope
- Material 3 theming
- Existing patterns: `StateFlow<UiState>`, `Resource<T>` sealed classes
- No new dependencies required (all Compose/Coroutines deps already present)

---

### Task 1: Create NetworkMonitor Interface

**Files:**
- Create: `Frontend/app/src/main/java/com/crm/v2/common/network/NetworkMonitor.kt`

**Interfaces:**
- Produces: `NetworkMonitor` interface with `val isConnected: StateFlow<Boolean>`

- [ ] **Step 1: Create the NetworkMonitor interface**

```kotlin
package com.crm.v2.common.network

import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val isConnected: StateFlow<Boolean>
}
```

- [ ] **Step 2: Verify file compiles**

Run: Check that the file has no syntax errors (no import issues, StateFlow is from kotlinx.coroutines.flow).

- [ ] **Step 3: Commit**

```bash
git add Frontend/app/src/main/java/com/crm/v2/common/network/NetworkMonitor.kt
git commit -m "feat: add NetworkMonitor interface for connectivity tracking"
```

---

### Task 2: Create NetworkMonitorImpl

**Files:**
- Create: `Frontend/app/src/main/java/com/crm/v2/common/network/NetworkMonitorImpl.kt`

**Interfaces:**
- Consumes: `NetworkMonitor` interface from Task 1
- Consumes: `android.net.ConnectivityManager` (injected via Hilt)
- Produces: `NetworkMonitorImpl` class implementing `NetworkMonitor`

- [ ] **Step 1: Create NetworkMonitorImpl**

```kotlin
package com.crm.v2.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitorImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkMonitor {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableStateFlow(checkCurrentConnectivity())
    override val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.value = true
        }

        override fun onLost(network: Network) {
            _isConnected.value = false
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val hasInternet = networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            )
            _isConnected.value = hasInternet
        }
    }

    init {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    private fun checkCurrentConnectivity(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
```

- [ ] **Step 2: Verify file compiles**

Run: Check imports are correct, `ConnectivityManager` methods exist on min SDK 24.

- [ ] **Step 3: Commit**

```bash
git add Frontend/app/src/main/java/com/crm/v2/common/network/NetworkMonitorImpl.kt
git commit -m "feat: add NetworkMonitorImpl using ConnectivityManager callbacks"
```

---

### Task 3: Add Hilt Binding for NetworkMonitor

**Files:**
- Modify: `Frontend/app/src/main/java/com/crm/v2/di/NetworkModule.kt` — add `@Binds` method

**Interfaces:**
- Consumes: `NetworkMonitor` interface (Task 1), `NetworkMonitorImpl` (Task 2)
- Produces: Hilt binding `NetworkMonitor` → `NetworkMonitorImpl`

- [ ] **Step 1: Add @Binds to NetworkModule**

Add this import at the top of `NetworkModule.kt`:
```kotlin
import com.crm.v2.common.network.NetworkMonitor
import com.crm.v2.common.network.NetworkMonitorImpl
import dagger.Binds
```

Add this method inside the `NetworkModule` object (after the existing `provideSearchApplicationApi` method):
```kotlin
@Binds
@Singleton
fun bindNetworkMonitor(impl: NetworkMonitorImpl): NetworkMonitor
```

- [ ] **Step 2: Verify binding compiles**

Run: Check that the `@Binds` annotation is correctly placed, no missing imports.

- [ ] **Step 3: Commit**

```bash
git add Frontend/app/src/main/java/com/crm/v2/di/NetworkModule.kt
git commit -m "feat: add Hilt binding for NetworkMonitor"
```

---

### Task 4: Create NoInternetOverlay Composable

**Files:**
- Create: `Frontend/app/src/main/java/com/crm/v2/presentation/common/components/NoInternetOverlay.kt`

**Interfaces:**
- Consumes: Material 3 theming (existing)
- Produces: `NoInternetOverlay` composable

- [ ] **Step 1: Create the NoInternetOverlay composable**

```kotlin
package com.crm.v2.presentation.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoInternetOverlay() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.WifiOff,
            contentDescription = "No internet",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "No Internet Connection",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Please check your network settings",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}
```

- [ ] **Step 2: Verify composable compiles**

Run: Check Material 3 imports resolve, `Icons.Default.WifiOff` exists in `material-icons-extended`.

- [ ] **Step 3: Commit**

```bash
git add Frontend/app/src/main/java/com/crm/v2/presentation/common/components/NoInternetOverlay.kt
git commit -m "feat: add NoInternetOverlay composable for offline state"
```

---

### Task 5: Integrate NetworkGate into MainActivity

**Files:**
- Modify: `Frontend/app/src/main/java/com/crm/v2/presentation/MainActivity.kt`

**Interfaces:**
- Consumes: `NetworkMonitor` (Task 1), `NoInternetOverlay` (Task 4)
- Produces: Network-aware MainActivity

- [ ] **Step 1: Add imports to MainActivity**

Add these imports at the top of `MainActivity.kt`:
```kotlin
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.crm.v2.common.network.NetworkMonitor
import com.crm.v2.presentation.common.components.NoInternetOverlay
```

- [ ] **Step 2: Inject NetworkMonitor**

Add this field injection alongside the existing `authDataStore` injection:
```kotlin
@Inject lateinit var networkMonitor: NetworkMonitor
```

- [ ] **Step 3: Add NetworkGate wrapper**

In the `setContent` block, wrap the existing `NavGraph` with the `NetworkGate` composable. The current code is:
```kotlin
if (criticalOk) {
    NavGraph(startDestination = startDestination)
} else {
    PermissionScreen(...)
}
```

Replace with:
```kotlin
if (criticalOk) {
    NetworkGate(monitor = networkMonitor) {
        NavGraph(startDestination = startDestination)
    }
} else {
    PermissionScreen(
        onRequestPermissions = { launchCriticalPermissions() },
        onOpenSettings = {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        }
    )
}
```

- [ ] **Step 4: Add the NetworkGate composable function**

Add this private composable at the bottom of `MainActivity.kt` (after the `PermissionScreen` composable):
```kotlin
@Composable
private fun NetworkGate(
    monitor: NetworkMonitor,
    content: @Composable () -> Unit
) {
    val isConnected by monitor.isConnected.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        content()

        AnimatedVisibility(
            visible = !isConnected,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            NoInternetOverlay()
        }
    }
}
```

- [ ] **Step 5: Add missing imports for new composable**

Add if not already present:
```kotlin
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
```

- [ ] **Step 6: Verify integration compiles**

Run: Check that all new imports resolve, `NetworkGate` is correctly referenced, no duplicate imports.

- [ ] **Step 7: Commit**

```bash
git add Frontend/app/src/main/java/com/crm/v2/presentation/MainActivity.kt
git commit -m "feat: integrate NetworkGate overlay into MainActivity"
```

---

### Task 6: End-to-End Verification

**Files:**
- No file changes — testing only

- [ ] **Step 1: Build the project**

Run: `./gradlew assembleDebug` from `Frontend/` directory
Expected: Build succeeds with no errors

- [ ] **Step 2: Manual test — offline behavior**

1. Install debug APK on device/emulator
2. Turn off WiFi and mobile data
3. Expected: Full-screen overlay appears with wifi-off icon and "No Internet Connection" text
4. Expected: App is not navigable behind the overlay

- [ ] **Step 3: Manual test — online behavior**

1. Turn WiFi back on
2. Expected: Overlay disappears automatically
3. Expected: App is at the same screen as before, fully functional

- [ ] **Step 4: Manual test — startup with no network**

1. Kill the app
2. Turn off WiFi/mobile data
3. Launch the app
4. Expected: Overlay appears immediately (after login/permissions)

- [ ] **Step 5: Commit final state**

```bash
git add -A
git commit -m "feat: network connectivity monitoring complete"
```
