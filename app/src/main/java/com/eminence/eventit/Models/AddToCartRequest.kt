package com.eminence.eventit.Models

data class AddToCartRequest(
    val user_id: String,
    val event_activity_id: String,
    val qty: String,
    val price: String,
    val date_slot: String,
    val time_slot: String


)
