package com.eminence.eventit.Models

data class TimeSlotResponse(
    val `data`: List<TimeSlotResponseListData>,
    val message: String,
    val status: Int
)