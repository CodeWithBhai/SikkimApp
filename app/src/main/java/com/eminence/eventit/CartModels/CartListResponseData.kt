package com.eminence.eventit.CartModels

import com.eminence.eventit.Activities.details.models.AddonX

data class CartListResponseData(
        val id: String,
        val cart_id: String,
        val event_name: String,
        val event_image: String,
        val place_name: String,
        val quantity: String,
        val price: String,
        val addon: List<AddonX>
)
