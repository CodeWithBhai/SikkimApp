package com.eminence.eventit.Activities.details.models

data class PlaceDetailsResponse(
    val about: String,
    val activity: List<Activity>,
    val banners: List<Banner>,
    val event: List<Event>,
    val gallery: List<Gallery>,
    val location: String,
    val main_image: String,
    val message: String,
    val name: String,
    val status: Int
)