package com.service.servicejunction.home.laundry.presentation

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable

@Composable
fun LaundryScreen() {

    val laundryTypes = listOf(
        "Home Laundry", "Hotels Laundry", "Offices Dry Cleaning",
        "Industry Dry Clean","Hospital Laundry"
    )

    LazyRow {

    }
}

//@Composable
//fun LaundryItem(){
//
//}

//data class LaundryItem( val laundryType: String, val typesCategory: List<String>)