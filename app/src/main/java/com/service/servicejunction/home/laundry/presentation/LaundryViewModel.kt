package com.service.servicejunction.home.laundry.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.service.servicejunction.R
import com.service.servicejunction.home.laundry.domain.local.LaundryItem
import com.service.servicejunction.home.laundry.domain.local.LaundryWithCategory
import com.service.servicejunction.home.laundry.domain.mapper.toLaundryCategory
import com.service.servicejunction.home.laundry.domain.repository.LaundryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaundryViewModel @Inject constructor(
    private val repository: LaundryRepository
) : ViewModel() {

    var state by mutableStateOf(LaundryState())

    private val laundryCategory = listOf(
        LaundryItem(
            Pair("Home Laundry", R.drawable.laundry_1),
            listOf(
                Pair("Traditional and daily wear clothes", R.drawable.ic_shirt),
                Pair("Bed-sheet laundry", R.drawable.ic_shirt),
                Pair("Curtain dry cleaning", R.drawable.ic_shirt),
                Pair("Kids wear", R.drawable.ic_shirt),
                Pair("Sofa & cover cleaner", R.drawable.sofa),
                Pair("Shoe laundering", R.drawable.ic_shirt)
            )
        ),
        LaundryItem(
            Pair("Hotels Laundry", R.drawable.laundry_1),
            listOf(
                Pair("Bed sheets", R.drawable.ic_shirt),
                Pair("Duvet covers", R.drawable.ic_shirt),
                Pair("Pillow cases", R.drawable.ic_shirt),
                Pair("Bath mats", R.drawable.ic_shirt),
                Pair("Towels", R.drawable.ic_shirt)
            )
        ),
        LaundryItem(
            Pair("Offices Dry Cleaning", R.drawable.laundry_1),
            listOf(
                Pair("Curtains", R.drawable.ic_shirt),
                Pair("Sofa", R.drawable.sofa),
                Pair("Floor mats", R.drawable.ic_shirt),
                Pair("Work outfits", R.drawable.ic_shirt)
            )
        ),
        LaundryItem(
            Pair("Industry Dry Clean", R.drawable.laundry_1),
            listOf(
                Pair("New fabric in textile", R.drawable.ic_shirt),
                Pair("First-Time wash", R.drawable.ic_shirt),
                Pair("Industrial aprons", R.drawable.ic_shirt),
                Pair("Worker pants", R.drawable.ic_shirt),
                Pair("Worker shirts", R.drawable.ic_shirt)
            )
        ),
        LaundryItem(
            Pair("Hospital Laundry", R.drawable.laundry_1),
            listOf(
                Pair("Curtains", R.drawable.ic_shirt),
                Pair("Drapes table clothes", R.drawable.ic_shirt),
                Pair("Bed-Sheet", R.drawable.ic_shirt),
                Pair("Doctors and medical staffs apron", R.drawable.ic_shirt),
                Pair("Body outfits", R.drawable.ic_shirt)
            )
        )
    )

    init {
        fetchCompanyListWithDetails()
    }

    private fun fetchCompanyListWithDetails() {
        viewModelScope.launch {
            val laundryListWithCategory = repository.getLaundryWithCategory(laundryCategory)
            state = state.copy(laundryListWithCategory = laundryListWithCategory)
        }
    }

//    fun getLaundryCategory(laundryId: Int) {
//        val selectedCategory = state.laundryListWithCategory
//            .map { laundryWithCategory ->
//                val filteredCategory = laundryWithCategory.category
//                    .filter { it.laundryId == laundryId }
//                laundryWithCategory.copy(category = filteredCategory)
//            }
//            .filter { it.category.isNotEmpty() }
//        val category = selectedCategory.flatMap { it.category }.map {
//            it.toLaundryCategory()
//        }
//        state = state.copy(laundryCategory = category)
//    }

    fun getLaundryCategory(laundryId: Int) {
        val selectedCategories = state.laundryListWithCategory
            .find { it.laundry.laundryId == laundryId }
            ?.category
            ?.map { it.toLaundryCategory() }
            ?: emptyList()

        state = state.copy(laundryCategory = selectedCategories)
    }

    fun updateLaundryCategory(
        categoryId: Int,
        laundryId: Int,
        categoryName: String,
        newCount: Int,
        iconRes: Int
    ) {

        viewModelScope.launch {
            repository.updateLaundryCategory(
                categoryId = categoryId,
                laundryId = laundryId,
                categoryName = categoryName,
                newCount = newCount,
                iconRes = iconRes
            )
            val updatedLaundryListWithCategory = repository.getLaundryWithCategory(laundryCategory)

            state = state.copy(laundryListWithCategory = updatedLaundryListWithCategory)

            val selectedCategory = updatedLaundryListWithCategory
                .map { laundryWithCategory ->
                    val filteredCategory =
                        laundryWithCategory.category.filter { it.laundryId == laundryId }
                    laundryWithCategory.copy(category = filteredCategory)
                }
                .filter { it.category.isEmpty() }
            val category = selectedCategory.flatMap { it.category }.map {
                it.toLaundryCategory()
            }


            state = state.copy(laundryCategory = category)
        }
    }

//

//    fun updateLaundryCategory(
//        categoryId: Int,
//        laundryId: Int,
//        categoryName: String,
//        newCount: Int,
//        iconRes: Int
//    ) {
//        // Update the count in the current state
//        val updatedCategories = state.laundryCategory.map { category ->
//            if (category.categoryId == categoryId) {
//                category.copy(count = newCount) // Create a new instance with the updated count
//            } else {
//                category
//            }
//        }
//
//        // Update the state to trigger recomposition
//        state = state.copy(laundryCategory = updatedCategories)
//
//        // Persist the changes in the repository
//        viewModelScope.launch {
//            repository.updateLaundryCategory(
//                categoryId = categoryId,
//                laundryId = laundryId,
//                categoryName = categoryName,
//                newCount = newCount,
//                iconRes = iconRes
//            )
//        }
//    }
}