package com.service.servicejunction.home.washing.cloths.branded.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.service.servicejunction.R
import com.service.servicejunction.home.washing.cloths.branded.domain.local.Item
import com.service.servicejunction.home.washing.cloths.branded.domain.repository.BrandWashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandWashClothViewModel @Inject constructor(
    private val repository: BrandWashRepository
) : ViewModel() {
    var state by mutableStateOf(BrandWashClothState())

    private val companyList = listOf(
        "Nike", "Adidas", "Puma", "Rebook", "Levi's", "Gucci",
        "Zara", "H&M", "Calvin Klein", "Tommy Hilfiger", "Louis Vuitton",
        "Prada", "Versace", "Burberry", "Armani", "Diesel", "Under Armour",
        "New Balance", "Uniglo", "Ralph Lauren"
    )

    val items = listOf(
        Item("Pant", 3.0, R.drawable.ic_shirt),
        Item("T-Shirt", 3.0, R.drawable.ic_shirt),
        Item("Shirt", 2.5, R.drawable.ic_shirt),
        Item("Jeans", 3.5, R.drawable.ic_shirt),
        Item("Short", 4.0, R.drawable.ic_shirt),
        Item("Jacket", 4.5, R.drawable.ic_shirt)
    )

    init {
        fetchCompanyListWithDetails()
    }

    fun getCompanyDetails(companyId: Int) {

        val selectedDetails = state.companyListWithDetails
            .map { companyWithDetails ->
                val filteredDetails = companyWithDetails.details.filter { it.brandId == companyId }
                companyWithDetails.copy(details = filteredDetails)
            }
            .filter { it.details.isNotEmpty() }

        state = state.copy(companyDetail = selectedDetails)
    }

    fun updateDetailCount(
        detailsId: Int,
        newCount: Int,
        companyId: Int,
        text: String,
        price: Double,
        iconRes: Int
    ) {
        viewModelScope.launch {
            repository.updateDetailCount(detailsId, newCount, companyId, text, price, iconRes)

            val updatedCompanyListWithDetails = repository.getCompanyWithDetails(companyList, items)

            state = state.copy(companyListWithDetails = updatedCompanyListWithDetails)

            val selectedDetails = updatedCompanyListWithDetails
                .map { companyWithDetails ->
                    val filteredDetails =
                        companyWithDetails.details.filter { it.brandId == companyId }
                    companyWithDetails.copy(details = filteredDetails)
                }
                .filter { it.details.isNotEmpty() }

            state = state.copy(companyDetail = selectedDetails)
        }
    }

    private fun fetchCompanyListWithDetails() {
        viewModelScope.launch {
            val companyListWithDetails = repository.getCompanyWithDetails(companyList, items)
            state = state.copy(companyListWithDetails = companyListWithDetails)
        }
    }

    fun getCompanyListWithDetails() {
        val filteredDetails = state.companyListWithDetails
            .map { companyWithDetails ->
                val filteredDetails = companyWithDetails.details.filter { it.count > 0 }
                companyWithDetails.copy(details = filteredDetails)
            }
            .filter { it.details.isNotEmpty() }

        state = state.copy(selectedCompanyDetails = filteredDetails)
    }

    fun getTotalCount(itemName: String): Int {
        return state.companyListWithDetails.sumOf { companyWithDetails ->
            companyWithDetails.details
                .filter { it.item.equals(itemName, ignoreCase = true) }
                .sumOf { it.count }
        }
    }

}