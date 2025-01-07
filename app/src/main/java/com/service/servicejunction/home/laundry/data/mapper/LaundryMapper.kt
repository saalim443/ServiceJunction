package com.service.servicejunction.home.laundry.data.mapper

import com.service.servicejunction.home.laundry.data.local.LaundryWithCategoryRelation
import com.service.servicejunction.home.laundry.domain.local.LaundryWithCategory

fun LaundryWithCategoryRelation.toLaundryWithCategory(): LaundryWithCategory {
    return LaundryWithCategory(
        laundry = laundry,
        category = category
    )
}