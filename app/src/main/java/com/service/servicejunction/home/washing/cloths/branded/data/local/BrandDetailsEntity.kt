package com.service.servicejunction.home.washing.cloths.branded.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BrandEntity::class,
            parentColumns = ["brandId"],
            childColumns = ["brandId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BrandDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val detailId : Int = 0,
    val brandId : Int,
    val item : String,
    val count : Int,
    val price : Double,
    val iconRes : Int
)