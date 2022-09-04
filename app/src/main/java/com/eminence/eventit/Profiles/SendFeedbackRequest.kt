package com.eminence.eventit.Profiles

data class SendFeedbackRequest(
    val user_id: String,
    val rating: String,
    val description: String
)
