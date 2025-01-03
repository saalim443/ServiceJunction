package com.service.servicejunction.home.laundry.presentation

import androidx.lifecycle.ViewModel
import com.service.servicejunction.home.laundry.domain.repository.LaundryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaundryViewModel @Inject constructor(
    private val repository: LaundryRepository
) : ViewModel() {

    val laundryTypes = listOf(
        "Hotels Laundry", "Offices Dry Cleaning",
        "Industry Dry Clean","Hospital Laundry"
    )

    val laundryCategory = listOf(
        LaundryItem("Home Laundry", listOf("Traditional and daily wear clothes", "Bed-sheet laundry", "Curtain dry cleaning", "Kids wear", "Sofa & cover cleaner", "Shoe laundering"))
    )

    val laundryDetailsItem = listOf("Sheets", "")
}

data class LaundryItem( val laundryType: String, val typesCategory: List<String>)