package com.crm.v2.data.config

data class CloudConnectConfig(
    // CloudConnect REST API
    val baseUrl: String = "https://crm2.cloud-connect.in/ccpl_api/v1.4/",
    val click2Call: String = "api/info/click2call",
    val tokenId: String = "6JU4KMMIO6B1xzLt",
    val extensionNumber: String = "802",
    val extensionPassword: String = "15e8e7c72ea16ca290930fd7c4db760b",

    // SIP settings
    val sipServer: String = "sip2.cloud-connect.in",
    val sipPort: String = "7065",
    val sipUsername: String = "102949802",
    val sipPassword: String = "Tejas@123",
    val sipRealm: String = "sip2.cloud-connect.in"
)
