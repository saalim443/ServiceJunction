package com.service.servicejunction.home.laundry.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithDetailsRelation (
    @Embedded val laundryCategory: LaundryCategoryEntity,
    @Relation(
        parentColumn = "laundryId",
        entityColumn = "laundryId"
    )
    val detailsEntity: List<LaundryDetailsEntity>
)