package com.service.servicejunction.home.washing.cloths.branded.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class BrandWithDetailsRelation (
    @Embedded val brand: BrandEntity,
    @Relation(
        parentColumn = "brandId",
        entityColumn = "brandId"
    )
    val details: List<BrandDetailsEntity>
)