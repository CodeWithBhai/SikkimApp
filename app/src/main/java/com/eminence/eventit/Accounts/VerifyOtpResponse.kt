package com.eminence.eventit.Accounts

data class VerifyOtpResponse(

    val status: String,
    val message: String,
    val user_id: String,
    val user_email: String

    )