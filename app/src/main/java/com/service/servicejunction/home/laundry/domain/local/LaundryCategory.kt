package com.service.servicejunction.home.laundry.domain.local

data class LaundryCategory (
    val categoryId : Int,
    val laundryId : Int,
    val categoryName : String,
    val count: Int,
    val iconRes: Int
)