package com.eminence.eventit.ui.home.model.category

data class CategoryListResponse(
    val `data`: List<CategoryListData>,
    val message: String,
    val status: Int
)