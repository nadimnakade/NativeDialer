package com.crm.v2.common.constants

object StatusConstants {
    const val NOT_INTERESTED = "NI"
    const val FOLLOW_UP = "FU"
    const val SAME_DAY_FOLLOW_UP = "SF"
    const val RINGING = "R"
    const val NOT_CONTACTABLE = "NC"
    const val DND = "DND"
    const val LEAD = "L"
    const val WRONG_NO = "WN"
    const val NOT_DOABLE = "ND"
    const val CALL_BACK = "CB"
    const val INTERESTED = "I"
    const val BLANK = "B"
    const val CONNECTED = "C"

    val allStatuses: List<StatusOption> = listOf(
        StatusOption(FOLLOW_UP, "Follow Up"),
        StatusOption(SAME_DAY_FOLLOW_UP, "SameDay Follow Up"),
        StatusOption(RINGING, "Ringing"),
        StatusOption(NOT_CONTACTABLE, "Not Contactable"),
        StatusOption(NOT_INTERESTED, "Not Interested"),
        StatusOption(DND, "DND"),
        StatusOption(LEAD, "Lead"),
        StatusOption(WRONG_NO, "Wrong No."),
        StatusOption(NOT_DOABLE, "Not Doable"),
    )
}

data class StatusOption(
    val value: String,
    val label: String,
)

object ProductConstants {
    const val HL = "HL"
    const val LAP = "LAP"
    const val BL = "BL"
    const val PL = "PL"
    const val CC = "CC"
}

object CallTypeConstants {
    const val INCOMING = "INCOMING"
    const val OUTGOING = "OUTGOING"
    const val MISSED = "MISSED"
    const val REJECTED = "REJECTED"
}
