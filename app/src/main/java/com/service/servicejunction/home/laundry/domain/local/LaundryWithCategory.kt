package com.service.servicejunction.home.laundry.domain.local

import com.service.servicejunction.home.laundry.data.local.LaundryCategoryEntity
import com.service.servicejunction.home.laundry.data.local.LaundryEntity

data class LaundryWithCategory(
    val laundry: LaundryEntity,
    val category: List<LaundryCategoryEntity>
)
