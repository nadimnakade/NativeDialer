package com.crm.v2.telephony

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.telephony.TelephonyManager
import com.crm.v2.ui.theme.CrmTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TestCallActivity : ComponentActivity() {

    @Inject lateinit var telephonyProvider: TelephonyManager
    @Inject lateinit var authDataStore: AuthDataStore

    private val requiredPermissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_PHONE_STATE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authDataStore.userKey = "test_user"
        authDataStore.isLoggedIn = true

        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 100)
        }

        kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.Main) {
            kotlinx.coroutines.delay(2000)
            try {
                val config = TelephonyConfig(
                    sipServer = ApiConstants.Sip.SERVER,
                    sipUsername = ApiConstants.Sip.USERNAME,
                    sipPassword = ApiConstants.Sip.PASSWORD
                )
                telephonyProvider.register(config)
            } catch (e: Exception) {
                android.util.Log.e("TestCallActivity", "Auto-register failed", e)
            }
        }

        setContent {
            CrmTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    if (hasPermissions()) {
                        TestDialpad(telephonyProvider = telephonyProvider)
                    } else {
                        Text(
                            "Grant permissions to make calls",
                            modifier = Modifier.padding(32.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                recreate()
            } else {
                Toast.makeText(this, "Permissions required for calling", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun hasPermissions(): Boolean = requiredPermissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}

data class Key(val label: String, val sub: String = "")

private val keys = listOf(
    Key("1"), Key("2", "ABC"), Key("3", "DEF"),
    Key("4", "GHI"), Key("5", "JKL"), Key("6", "MNO"),
    Key("7", "PQRS"), Key("8", "TUV"), Key("9", "WXYZ"),
    Key("*"), Key("0", "+"), Key("#"),
)

@Composable
fun TestDialpad(telephonyProvider: TelephonyManager) {
    var number by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Ready") }
    var callState by remember { mutableStateOf<CallState>(CallState.Idle) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        telephonyProvider.callState.collectLatest { state ->
            callState = state
            status = when (state) {
                is CallState.Idle -> "Ready"
                is CallState.Registered -> "Registered"
                is CallState.Calling -> "Calling..."
                is CallState.Ringing -> "Ringing..."
                is CallState.Connected -> "Connected"
                is CallState.OnHold -> "On Hold"
                is CallState.Error -> "Error"
                is CallState.Registering -> "Registering..."
            }
        }
    }

    val isActive = callState.isActive

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text("Test Dialer", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        val statusColor = when (callState) {
            is CallState.Connected -> Color(0xFF4CAF50)
            is CallState.Error -> Color(0xFFE53935)
            is CallState.Calling, is CallState.Ringing -> Color(0xFFFFC107)
            else -> Color.Gray
        }
        Text(status, style = MaterialTheme.typography.bodyMedium, color = statusColor)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1A1A2E))
                .border(1.dp, Color(0xFF333), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = number.ifEmpty { "Enter number" },
                fontSize = 28.sp,
                color = if (number.isEmpty()) Color.Gray else Color.White,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(keys) { key ->
                KeyButton(key = key.label, sub = key.sub) {
                    if (!isActive) number += key.label
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (number.isNotEmpty()) {
                        scope.launch {
                            if (callState is CallState.Registered) {
                                status = "Calling $number..."
                                telephonyProvider.call(number)
                            } else {
                                status = "Registering..."
                                try {
                                    val config = TelephonyConfig(
                                        sipServer = ApiConstants.Sip.SERVER,
                                        sipUsername = ApiConstants.Sip.USERNAME,
                                        sipPassword = ApiConstants.Sip.PASSWORD
                                    )
                                    telephonyProvider.register(config)

                                    var waited = 0
                                    while (callState !is CallState.Registered && waited < 100) {
                                        kotlinx.coroutines.delay(100)
                                        waited++
                                    }
                                    if (callState is CallState.Registered) {
                                        status = "Calling $number..."
                                        telephonyProvider.call(number)
                                    } else {
                                        status = "Registration timeout (state=$callState)"
                                    }
                                } catch (e: Exception) {
                                    status = "Error: ${e.message}"
                                }
                            }
                        }
                    }
                },
                enabled = !isActive && number.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier.size(width = 120.dp, height = 48.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Call", fontWeight = FontWeight.Bold, color = Color.White)
            }

            Button(
                onClick = {
                    scope.launch {
                        telephonyProvider.hangup()
                        number = ""
                        status = "Ready"
                    }
                },
                enabled = isActive,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                modifier = Modifier.size(width = 120.dp, height = 48.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(if (callState is CallState.Calling || callState is CallState.Ringing) "Cancel" else "Hang Up",
                    fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { number = number.dropLast(1) },
                enabled = !isActive && number.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier.size(width = 100.dp, height = 40.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("⌫", color = Color.White)
            }
            Button(
                onClick = { number = "" },
                enabled = !isActive && number.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier.size(width = 100.dp, height = 40.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Clear", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun KeyButton(key: String, sub: String = "", onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .aspectRatio(1.3f)
            .clip(CircleShape)
            .background(Color(0xFF1A1A2E))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(key, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Normal)
        if (sub.isNotEmpty()) {
            Text(sub, fontSize = 9.sp, color = Color.Gray, letterSpacing = 2.sp)
        }
    }
}
