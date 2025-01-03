package com.service.servicejunction.home.laundry.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandDetailsEntity

@Dao
interface LaundryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaundryTypes(laundryEntity: LaundryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaundryCategory(categoryEntity: List<LaundryCategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryDetails(detailsEntity: List<LaundryDetailsEntity>)
}