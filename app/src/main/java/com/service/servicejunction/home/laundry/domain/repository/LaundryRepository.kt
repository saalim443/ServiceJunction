package com.service.servicejunction.home.laundry.domain.repository

import com.service.servicejunction.home.laundry.domain.local.LaundryCategory
import com.service.servicejunction.home.laundry.domain.local.LaundryItem
import com.service.servicejunction.home.laundry.domain.local.LaundryWithCategory

interface LaundryRepository {
    suspend fun getLaundryWithCategory(laundryList: List<LaundryItem>): List<LaundryWithCategory>

    suspend fun updateLaundryCategory(categoryId: Int, laundryId: Int, categoryName: String, newCount: Int, iconRes :Int)

//    suspend fun getLaundryCategory(laundryId: Int): List<LaundryCategory>
}