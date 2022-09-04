package com.eminence.eventit.ui.home.model.tapvisited

data class TopVisitedResponse(
    val `data`: List<TopVisitedListData>,
    val message: String,
    val status: Int
)