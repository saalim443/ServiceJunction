package com.service.servicejunction.home.laundry.domain.repository

import com.service.servicejunction.home.washing.cloths.branded.domain.local.BrandWithDetails
import com.service.servicejunction.home.washing.cloths.branded.domain.local.Item

interface LaundryRepository {
    suspend fun getCompanyWithDetails(brandList: List<String>, brandDetails: List<Item>): List<BrandWithDetails>
}