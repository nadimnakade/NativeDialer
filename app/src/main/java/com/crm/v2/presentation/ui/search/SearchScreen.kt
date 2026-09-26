package com.crm.v2.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.ClientListItem

@Composable
fun SearchScreen(
    onCall: (String) -> Unit = {},
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC)).padding(16.dp)) {
        OutlinedTextField(
            value = uiState.query,
            onValueChange = { viewModel.onQueryChange(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search client name or mobile...", color = Color(0xFF94A3B8)) },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null, tint = Color(0xFF94A3B8)) },
            trailingIcon = {
                if (uiState.query.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onQueryChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear", tint = Color(0xFF94A3B8))
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE2E8F0),
                focusedBorderColor = Color(0xFFDE3121),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                cursorColor = Color(0xFFDE3121),
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (uiState.query.trim().length in 0..3 && uiState.query.isNotEmpty()) {
            Text(
                "Type more than 3 characters to search",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8),
                modifier = Modifier.padding(top = 8.dp),
            )
        }

        uiState.error?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2)),
            ) {
                Text(error, modifier = Modifier.padding(12.dp), color = Color(0xFFDC2626), style = MaterialTheme.typography.bodySmall)
            }
        }

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxWidth().padding(top = 48.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFFDE3121))
            }
        }

        if (uiState.query.trim().length > 3 && !uiState.isLoading && uiState.results.isEmpty() && uiState.error == null) {
            Box(modifier = Modifier.fillMaxWidth().padding(top = 48.dp), contentAlignment = Alignment.Center) {
                Text("No clients found", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF94A3B8))
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 8.dp),
        ) {
            items(uiState.results, key = { it.key }) { client ->
                ClientListItem(
                    name = client.name ?: "Unknown",
                    phone = (client.mobileNo ?: "").maskPhone(),
                    onCall = {
                        val phone = client.mobileNo
                        if (!phone.isNullOrBlank()) onCall(phone)
                    },
                )
            }
        }
    }
}
