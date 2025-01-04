package com.service.servicejunction.home.laundry.domain.local

import androidx.room.Embedded
import androidx.room.Relation
import com.service.servicejunction.home.laundry.data.local.LaundryCategoryEntity
import com.service.servicejunction.home.laundry.data.local.LaundryEntity

data class LaundryWithCategory(
    val laundry: LaundryEntity,
    val category: List<LaundryCategoryEntity>
)
