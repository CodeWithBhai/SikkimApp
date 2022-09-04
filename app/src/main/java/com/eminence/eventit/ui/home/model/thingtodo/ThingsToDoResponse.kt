package com.eminence.eventit.ui.home.model.thingtodo

data class ThingsToDoResponse(
    val `data`: List<ThingsToDoListData>,
    val message: String,
    val status: Int
)