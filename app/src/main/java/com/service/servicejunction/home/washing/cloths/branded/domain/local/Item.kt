package com.service.servicejunction.home.washing.cloths.branded.domain.local

data class Item(val name: String, val price: Double, val iconRes: Int)

data class Brand(val name: String, var isSelected: Boolean = false)

