package com.service.servicejunction.home.laundry.presentation

import com.service.servicejunction.home.laundry.domain.local.LaundryCategory
import com.service.servicejunction.home.laundry.domain.local.LaundryWithCategory

data class LaundryState (
//    val selectedLaundryType: List<LaundryWithCategory> = emptyList(),
    val laundryListWithCategory: List<LaundryWithCategory> = emptyList(),
    val laundryCategory: List<LaundryCategory> = emptyList(),
//    val laundry: List<LaundryCategory> = emptyList()
)