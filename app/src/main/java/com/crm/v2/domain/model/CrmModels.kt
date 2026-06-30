package com.crm.v2.domain.model

import com.crm.v2.common.constants.StatusConstants

data class Lead(
    val key: String,
    val clientName: String,
    val contact: String,
    val product: String? = null,
    val salary: String? = null,
    val company: String? = null,
    val status: String? = null,
    val callerName: String? = null,
    val callbackDate: String? = null,
    val isHLLAPBL: String? = null,
    val isCibilBad: Boolean = false,
    val isRealEstate: Boolean = false,
    val remarks: String? = null,
    val createdDate: String? = null,
    val userKey: String? = null,
)

data class LeadCount(
    val daily: Int = 0,
    val monthly: Int = 0,
    val total: Int = 0,
)

data class DialerRecord(
    val key: String,
    val clientName: String,
    val contact: String,
    val product: String? = null,
    val campaign: String? = null,
    val salary: String? = null,
    val company: String? = null,
    val status: String? = null,
    val callbackDate: String? = null,
    val remarks: String? = null,
    val userKey: String? = null,
) {
    val statusLabel: String get() = when (status) {
        StatusConstants.NOT_INTERESTED -> "Not Interested"
        StatusConstants.FOLLOW_UP -> "Follow Up"
        StatusConstants.SAME_DAY_FOLLOW_UP -> "SameDay Follow Up"
        StatusConstants.RINGING -> "Ringing"
        StatusConstants.NOT_CONTACTABLE -> "Not Contactable"
        StatusConstants.DND -> "DND"
        StatusConstants.LEAD -> "Lead"
        StatusConstants.WRONG_NO -> "Wrong No."
        StatusConstants.NOT_DOABLE -> "Not Doable"
        else -> "Pending"
    }
}

data class CallLog(
    val key: String? = null,
    val name: String? = null,
    val number: String? = null,
    val type: String? = null,
    val duration: Int = 0,
    val callDate: String? = null,
    val userKey: String? = null,
)

data class PickupEntry(
    val key: String,
    val callerName: String? = null,
    val clientName: String? = null,
    val clientMobile: String? = null,
    val product: String? = null,
    val banks: String? = null,
    val status: String? = null,
    val pickupDate: String? = null,
    val region: String? = null,
    val pickupType: String? = null,
    val remarks: String? = null,
)

data class DailyLogin(
    val key: String? = null,
    val clientName: String? = null,
    val mobileNo: String? = null,
    val product: String? = null,
    val banks: String? = null,
    val losNo: String? = null,
    val appNo: String? = null,
    val region: String? = null,
    val loginAmount: Double = 0.0,
    val remarks: String? = null,
)

data class Contact(
    val id: String? = null,
    val name: String? = null,
    val phoneNumbers: List<String> = emptyList(),
    val email: String? = null,
    val photoUri: String? = null,
)

data class Document(
    val key: String? = null,
    val clientName: String? = null,
    val mobileNo: String? = null,
    val fileName: String? = null,
    val fileType: String? = null,
    val fileData: String? = null,
)

data class ClientInfo(
    val key: String,
    val name: String? = null,
    val mobileNo: String? = null,
    val documents: List<Document> = emptyList(),
)
