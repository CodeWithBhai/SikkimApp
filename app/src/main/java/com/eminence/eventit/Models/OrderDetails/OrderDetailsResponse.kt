package com.eminence.eventit.Models.OrderDetails

import com.eminence.eventit.Models.MyTicketModels.MyTicketResponseData

data class OrderDetailsResponse(
        val status: String,
        val message: String,
        val order_unique_id: String,
        val total_tickets: String,
        val total_addon: String,
        val place_name: String,
        val category_name: String,
        val place_image: String,
        val map_address: String,
        val ticket_total_amount: String,
        val gst: String,
        val booking_charges: String,
        val total_amount: String,
        val created_at: String,
        val `details`: List<OrderDetailsResponseData>
)
