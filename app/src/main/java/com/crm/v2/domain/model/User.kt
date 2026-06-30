package com.crm.v2.domain.model

data class User(
    val key: String,
    val employeeName: String,
    val userName: String,
    val designation: String,
    val role: String,
    val token: String,
    val tokenExpiry: Long
)
