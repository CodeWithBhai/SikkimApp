package com.eminence.eventit.Category

import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData

data class CategoryListByIdResponse(
    val status:String,
    val message: String,
    val `data`: List<CategoryListByIdData>


    )
