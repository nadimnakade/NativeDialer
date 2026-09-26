package com.crm.v2.common.constants

object StatusConstants {
    const val NOT_INTERESTED = "Not Interested"
    const val FOLLOW_UP = "Follow Up"
    const val SAME_DAY_FOLLOW_UP = "SameDay Follow Up"
    const val RINGING = "Ringing"
    const val NOT_CONTACTABLE = "Not Contactable"
    const val DND = "DND"
    const val LEAD = "Lead"
    const val WRONG_NO = "Wrong No."
    const val NOT_DOABLE = "Not Doable"
    const val CALL_BACK = "CB"
    const val INTERESTED = "I"
    const val BLANK = "B"
    const val CONNECTED = "C"

    // Statuses when call was NOT connected (duration <= 0)
    val notConnectedStatuses: List<StatusOption> = listOf(
        StatusOption(RINGING, "Ringing"),
        StatusOption(NOT_CONTACTABLE, "Not Contactable"),
    )

    // Statuses when call WAS connected (duration > 0)
    val connectedStatuses: List<StatusOption> = listOf(
        StatusOption(FOLLOW_UP, "Follow Up"),
        StatusOption(SAME_DAY_FOLLOW_UP, "SameDay Follow Up"),
        StatusOption(NOT_INTERESTED, "Not Interested"),
        StatusOption(DND, "DND"),
        StatusOption(LEAD, "Lead"),
        StatusOption(WRONG_NO, "Wrong No."),
        StatusOption(NOT_DOABLE, "Not Doable"),
    )

    val allStatuses: List<StatusOption> = notConnectedStatuses + connectedStatuses
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
