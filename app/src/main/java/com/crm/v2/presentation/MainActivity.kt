package com.crm.v2.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.presentation.navigation.NavGraph
import com.crm.v2.presentation.navigation.Screen
import com.crm.v2.telephony.pjsip.PjsipManager
import com.crm.v2.ui.theme.CrmTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var authDataStore: AuthDataStore
    @Inject lateinit var pjsipManager: PjsipManager

    private var permissionTick by mutableIntStateOf(0)

    private val criticalPermissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_PHONE_STATE,
    )

    private val optionalPermissions = arrayOf(
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.WRITE_CALL_LOG,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.POST_NOTIFICATIONS,
    )

    private var criticalRequestInFlight = false
    private var optionalRequestInFlight = false
    private var optionalLaunchedOnce = false

    private val criticalLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        criticalRequestInFlight = false
        permissionTick++
        if (results.values.all { it }) {
            pjsipManager.onPermissionGranted()
            launchOptionalPermissions()
        } else {
            Log.w(TAG, "Critical permissions denied: ${results.filter { !it.value }.keys}")
        }
    }

    private val optionalLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        optionalRequestInFlight = false
        permissionTick++
        val denied = results.filter { !it.value }.keys
        if (denied.isNotEmpty()) {
            Log.w(TAG, "Optional permissions denied: $denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val startDestination = if (authDataStore.isLoggedIn) {
            Screen.Dashboard.route
        } else {
            Screen.Login.route
        }

        setContent {
            CrmTheme {
                val tick = permissionTick
                val criticalOk = tick >= 0 && criticalGranted()

                if (criticalOk) {
                    NavGraph(startDestination = startDestination)
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
            }
        }

        launchCriticalPermissions()
    }

    override fun onResume() {
        super.onResume()
        permissionTick++
        if (criticalGranted()) {
            pjsipManager.onPermissionGranted()
        }
    }

    private fun criticalGranted(): Boolean = criticalPermissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun launchCriticalPermissions() {
        if (criticalRequestInFlight) return
        val missing = criticalPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isNotEmpty()) {
            criticalRequestInFlight = true
            criticalLauncher.launch(missing.toTypedArray())
        } else {
            permissionTick++
            pjsipManager.onPermissionGranted()
            launchOptionalPermissions()
        }
    }

    private fun launchOptionalPermissions() {
        if (optionalRequestInFlight || optionalLaunchedOnce) return
        val missing = optionalPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isNotEmpty()) {
            optionalRequestInFlight = true
            optionalLaunchedOnce = true
            optionalLauncher.launch(missing.toTypedArray())
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

@Composable
private fun PermissionScreen(
    onRequestPermissions: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Permissions Required",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "To make calls, this app needs:\n\n" +
                    "\u2022 Microphone (audio during calls)\n" +
                    "\u2022 Phone (make/manage calls)\n" +
                    "\u2022 Phone state (identify call status)",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRequestPermissions,
            modifier = Modifier
                .size(width = 280.dp, height = 56.dp)
        ) {
            Text("Grant Permissions", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onOpenSettings,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .size(width = 280.dp, height = 56.dp)
        ) {
            Text("Open Settings", style = MaterialTheme.typography.titleMedium)
        }
    }
}
