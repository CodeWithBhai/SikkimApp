package com.eminence.eventit.Models.MyTicketModels

data class MyTicketResponse(
        val status: Int,
        val message: String,
        val `data`: List<MyTicketResponseData>
)
