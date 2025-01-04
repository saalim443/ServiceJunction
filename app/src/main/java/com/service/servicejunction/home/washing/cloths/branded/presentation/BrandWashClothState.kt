package com.service.servicejunction.home.washing.cloths.branded.presentation

import com.service.servicejunction.home.washing.cloths.branded.domain.local.BrandWithDetails

data class BrandWashClothState (
    val selectedCompanyDetails: List<BrandWithDetails> = emptyList(),
    val companyListWithDetails: List<BrandWithDetails> = emptyList(),
    val companyDetail: List<BrandWithDetails> = emptyList()
)