package com.eminence.eventit.Models.OrderDetails

data class TicketsXY(
        val ticket_number: String,
        val event_activity_id: String,
        val activity_event_name: String,
        val ticket_fees: String,
        val date_slot: String,
        val time_slot: String,
        val status: String
)
