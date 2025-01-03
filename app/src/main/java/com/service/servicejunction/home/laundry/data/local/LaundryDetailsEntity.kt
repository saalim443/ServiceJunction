package com.service.servicejunction.home.laundry.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = LaundryCategoryEntity::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LaundryDetailsEntity (
    @PrimaryKey(autoGenerate = true)
    val detailId : Int = 0,
    val categoryId : Int,
    val detailName : String,
    val detailCount : Int
)