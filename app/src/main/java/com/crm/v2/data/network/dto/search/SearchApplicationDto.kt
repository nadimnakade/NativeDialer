package com.crm.v2.data.network.dto.search

data class SearchApplicationResponse(
    val iTotalRecords: Int = 0,
    val iTotalDisplayRecords: Int = 0,
    val aaData: List<SearchApplicationDto> = emptyList(),
)

data class SearchApplicationDto(
    val uniqId: String = "",
    val appNo: String = "",
    val appDate: String = "",
    val losNo: String = "",
    val loanName: String = "",
    val loanAmount: String = "",
    val clientName: String = "",
    val bankName: String = "",
    val disbursedAmount: String = "",
    val disbursalDate: String = "",
    val callerName: String = "",
    val cpvStatus: String = "",
    val remarks: String = "",
    val statusDesc: String = "",
    val updatedOn: String = "",
)
