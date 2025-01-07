package com.service.servicejunction.home.laundry.domain.mapper

import com.service.servicejunction.home.laundry.data.local.LaundryCategoryEntity
import com.service.servicejunction.home.laundry.domain.local.LaundryCategory

fun LaundryCategoryEntity.toLaundryCategory(): LaundryCategory {
    return LaundryCategory(
        laundryId = laundryId,
        categoryName = categoryName,
        count = count,
        categoryId = categoryId,
        iconRes = iconRes
    )
}