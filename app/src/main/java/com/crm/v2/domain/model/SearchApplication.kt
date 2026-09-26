package com.crm.v2.domain.model

data class SearchApplication(
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
