package com.crm.v2.telephony.pjsip

data class SipAccountConfig(
    val accountId: String,
    val registrarUri: String,
    val username: String,
    val password: String,
    val proxyUri: String = "",
    val realm: String = "*",
    val iceEnabled: Boolean = true,
    val stunServer: String = "",
)
