package com.eminence.eventit.ui.home.model.upcoming

data class UpcomingEventResponse(
    val `data`: List<UpcomingEventListData>,
    val message: String,
    val status: Int
)