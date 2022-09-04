package com.eminence.eventit.Models.eventDetails

data class EventDetailsResponse(
    val about: String,
    val location: String,
    val main_image: String,
    val message: String,
    val name: String,
    val place_name: String,
    val status: Int,
    val type: String
)