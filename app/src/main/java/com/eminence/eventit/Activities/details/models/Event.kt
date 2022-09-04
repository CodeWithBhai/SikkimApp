package com.eminence.eventit.Activities.details.models

data class Event(
    val about: String,
    val addon: List<AddonX>,
    val city: String,
    val dates: String,
    val id: String,
    val location: String,
    val main_image: String,
    val name: String,
    val price: String,
    val cart_id:Int
)