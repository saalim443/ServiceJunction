package com.service.servicejunction.home.washing.cloths.branded.domain.repository

import com.service.servicejunction.home.washing.cloths.branded.domain.local.BrandWithDetails
import com.service.servicejunction.home.washing.cloths.branded.domain.local.Item

interface BrandWashRepository {

    suspend fun getCompanyWithDetails(brandList: List<String>, brandDetails: List<Item>): List<BrandWithDetails>

    suspend fun updateDetailCount(detailsId: Int, newCount: Int, companyId: Int, text: String, price: Double, iconRes: Int)

}