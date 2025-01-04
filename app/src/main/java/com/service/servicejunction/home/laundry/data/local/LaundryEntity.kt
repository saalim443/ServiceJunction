package com.service.servicejunction.home.laundry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LaundryEntity (
    @PrimaryKey(autoGenerate = true)
    val laundryId : Int = 0,
    val laundryName : String,
    val iconRes: Int
)