package com.eminence.eventit.ui.home.model.banner

data class BannerResponse(
    val `data`: List<BannerListData>,
    val message: String,
    val status: Int
)