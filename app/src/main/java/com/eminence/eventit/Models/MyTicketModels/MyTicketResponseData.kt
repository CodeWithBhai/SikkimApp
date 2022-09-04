package com.eminence.eventit.Models.MyTicketModels

data class MyTicketResponseData(
        val order_unique_id: String,
        val total_tickets: String,
        val place_name: String,
        val category_name: String,
        val place_image: String,
        val created_at: String
)
