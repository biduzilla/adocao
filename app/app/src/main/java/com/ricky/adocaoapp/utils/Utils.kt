package com.ricky.adocaoapp.utils

fun formatPhoneNumber(phoneNumber: String): String {
    val digits = phoneNumber.filter { it.isDigit() }
    val paddedDigits = digits.padEnd(11, '0')
    return buildString {
        for (i in paddedDigits.indices) {
            when (i) {
                0 -> append('(')
                2 -> append(") ")
                5 -> append(' ')
                6 -> append(' ')
                9 -> append('-')
            }
            append(paddedDigits[i])
            if (i == 10) break
        }
    }
}
