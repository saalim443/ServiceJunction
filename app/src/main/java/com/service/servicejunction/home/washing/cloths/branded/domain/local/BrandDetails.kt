package com.service.servicejunction.home.washing.cloths.branded.domain.local

data class BrandDetails (
    val detailId : Int,
    val brandId : Int,
    val item : String,
    val count : Int,
    val price : Double,
    val iconRes : Int
)