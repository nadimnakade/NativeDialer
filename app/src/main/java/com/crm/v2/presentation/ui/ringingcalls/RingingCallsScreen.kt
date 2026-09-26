package com.crm.v2.presentation.ui.ringingcalls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.*

@Composable
fun RingingCallsScreen(
    onCall: (String) -> Unit,
    viewModel: RingingCallsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) { viewModel.load() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it, duration = SnackbarDuration.Short)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { ErrorSnackbarHost(snackbarHostState) },
    ) { padding ->
        PullRefreshListWrapper(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.load() },
            modifier = Modifier.padding(padding),
        ) {
            when {
                uiState.isLoading && uiState.leads.isEmpty() -> CrmLoading()
                uiState.leads.isEmpty() -> CrmEmptyState("No ringing calls")
                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC)),
                ) {
                    item { SectionHeader(title = "Recent calls") }
                    items(uiState.leads, key = { it.key }) { lead ->
                        ClientListItem(
                            name = lead.clientName,
                            phone = lead.contact.maskPhone(),
                            subtitle = lead.remarks,
                            onCall = { onCall(lead.contact) },
                        )
                    }
                }
            }
        }
    }
}
