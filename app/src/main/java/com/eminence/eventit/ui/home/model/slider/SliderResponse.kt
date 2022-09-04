package com.eminence.eventit.ui.home.model.slider

data class SliderResponse(
    val `data`: List<SliderData>,
    val message: String,
    val status: Int
)