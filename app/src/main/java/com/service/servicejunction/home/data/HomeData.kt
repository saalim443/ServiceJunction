package com.service.servicejunction.home.data

// Sample data class for the banners
data class BannerData(
    val title: String,
    val subtitle: String,
    val imageRes: Int // Resource ID for the image
)


data class GridItem(val title: String, val imageRes: Int)