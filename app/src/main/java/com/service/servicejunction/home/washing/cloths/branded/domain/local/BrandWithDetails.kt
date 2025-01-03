package com.service.servicejunction.home.washing.cloths.branded.domain.local

import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandDetailsEntity
import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandEntity

data class BrandWithDetails (
    val brand: BrandEntity,
    val details: List<BrandDetailsEntity>
)