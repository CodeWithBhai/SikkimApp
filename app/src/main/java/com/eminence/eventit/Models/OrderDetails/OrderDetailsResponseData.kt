package com.eminence.eventit.Models.OrderDetails

import com.eminence.eventit.Activities.details.models.AddonX

data class OrderDetailsResponseData(
        val event_activity_id: String,
        val activity_event_name: String,
        val type: String,
        val ticket_fees: String,
        val qty: String,
        val date_slot: String,
        val time_slot: String,
        val total_addon_qty: String,
        val addon: List<AddonXY>,
        val tickets: List<TicketsXY>
)
