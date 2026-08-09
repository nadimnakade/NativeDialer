package com.crm.v2.common.util

fun String.maskPhone(): String {
    val digits = this.filter { it.isDigit() }
    if (digits.length < 7) return this
    val prefix = digits.take(5)
    val suffix = digits.takeLast(2)
    val masked = "*".repeat(digits.length - 7)
    return "$prefix$masked$suffix"
}
