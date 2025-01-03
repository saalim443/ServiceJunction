package com.service.servicejunction.home.washing.cloths.branded.data.mapper

import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandWithDetailsRelation
import com.service.servicejunction.home.washing.cloths.branded.domain.local.BrandWithDetails

fun BrandWithDetailsRelation.toBrandWithDetails(): BrandWithDetails {
    return BrandWithDetails(
        brand = brand,
        details = details
    )
}