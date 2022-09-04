package com.eminence.eventit.Profiles

data class VerifySignUpOtpRequest(
    val logid: String,
    val otp: String,
    val mobile: String,
    val name: String,
    val email: String
)
