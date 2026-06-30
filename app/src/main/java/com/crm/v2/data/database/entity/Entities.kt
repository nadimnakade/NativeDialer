package com.crm.v2.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leads")
data class LeadEntity(
    @PrimaryKey val key: String,
    val clientName: String?,
    val contact: String?,
    val product: String?,
    val salary: String?,
    val company: String?,
    val status: String?,
    val callerName: String?,
    val callbackDate: String?,
    val isHLLAPBL: String?,
    val isCibilBad: Boolean = false,
    val isRealEstate: Boolean = false,
    val remarks: String?,
    val createdDate: String?,
    val userKey: String?,
    val cachedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "call_logs")
data class CallLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val key: String?,
    val name: String?,
    val number: String?,
    val type: String?,
    val duration: Int = 0,
    val callDate: String?,
    val userKey: String?,
    val synced: Boolean = false,
    val cachedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "dialer_data")
data class DialerDataEntity(
    @PrimaryKey val key: String,
    val clientName: String?,
    val contact: String?,
    val product: String?,
    val campaign: String?,
    val salary: String?,
    val company: String?,
    val status: String?,
    val callbackDate: String?,
    val remarks: String?,
    val userKey: String?,
    val cachedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val phoneNumbers: String?,
    val email: String?,
    val photoUri: String?,
    val cachedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "sync_queue")
data class SyncQueueEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val entityType: String,
    val action: String,
    val payload: String,
    val retryCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
)
