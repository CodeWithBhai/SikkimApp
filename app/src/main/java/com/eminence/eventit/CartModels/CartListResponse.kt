package com.eminence.eventit.CartModels

import com.eminence.eventit.Category.CategoryListByIdData

data class CartListResponse(
        val status: String,
        val message: String,
        val `data`: List<CartListResponseData>
)
