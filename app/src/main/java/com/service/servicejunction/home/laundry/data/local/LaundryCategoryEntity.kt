package com.service.servicejunction.home.laundry.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = LaundryEntity::class,
            parentColumns = ["laundryId"],
            childColumns = ["laundryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LaundryCategoryEntity (
    @PrimaryKey(autoGenerate = true)
    val categoryId : Int = 0,
    val laundryId : Int,
    val categoryName : String,
)