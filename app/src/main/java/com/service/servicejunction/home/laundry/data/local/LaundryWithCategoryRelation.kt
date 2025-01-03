package com.service.servicejunction.home.laundry.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class LaundryWithCategoryRelation (
    @Embedded val laundry: LaundryEntity,
    @Relation(
        parentColumn = "laundryId",
        entityColumn = "laundryId"
    )
    val category: List<LaundryCategoryEntity>
)