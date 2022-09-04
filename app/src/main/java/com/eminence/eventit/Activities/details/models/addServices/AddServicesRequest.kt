package com.eminence.eventit.Activities.details.models.addServices

data class AddServicesRequest(
    val date_slot: String,
    val event_activity_id: String,
    val price: String,
    val qty: String,
    val time_slot: String,
    val user_id: String
)