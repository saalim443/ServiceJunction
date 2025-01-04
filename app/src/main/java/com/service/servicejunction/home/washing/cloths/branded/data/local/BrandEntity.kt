package com.service.servicejunction.home.washing.cloths.branded.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BrandEntity (
    @PrimaryKey(autoGenerate = true)
    val brandId : Int = 0,
    val bandName : String
)