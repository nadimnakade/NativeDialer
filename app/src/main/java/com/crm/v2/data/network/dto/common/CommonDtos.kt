package com.crm.v2.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean = true,
    val message: String? = null,
    val data: T? = null,
)

@Serializable
data class PaginatedResponse<T>(
    val data: List<T> = emptyList(),
    val totalCount: Int = 0,
    val pageIndex: Int = 0,
    val pageSize: Int = 20,
)
