package com.eminence.eventit.CartModels

data class BookTicketRequest(
        val user_id: String,
        val total_tickets: String,
        val transaction_id: String,
        val total_amount: String,
        val gst: String,
        val booking_charges: String
)
