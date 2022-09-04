package com.eminence.eventit.Category.model.categoryListByCity

data class CategoryListByCityResponse(
    val `data`: List<CategoryListByCityData>,
    val message: String,
    val status: Int
)