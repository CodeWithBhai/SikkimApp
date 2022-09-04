package com.eminence.eventit.Accounts

data class VerifyOtpRequest (
    val logid: String,
    val otp: String,
    val mobile: String


)