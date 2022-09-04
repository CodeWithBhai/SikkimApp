package com.eminence.eventit.Activities.details

data class PlaceDetailTotalResponse(
    val status:String,
    val message:String,
    val sub_total:String,
    val gst:String,
    val booking_charges:String,
    val total_tickets:String,
    val total_amount:String,
)
