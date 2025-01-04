package com.service.servicejunction.home.laundry.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface LaundryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaundryTypes(laundryEntity: LaundryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaundryCategory(categoryEntity: List<LaundryCategoryEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCategoryDetails(detailsEntity: List<LaundryDetailsEntity>)

    @Transaction
    @Query("SELECT * FROM laundryentity")
    suspend fun getLaundryWithCategory(): List<LaundryWithCategoryRelation>

//    @Query("SELECT * FROM laundrycategoryentity WHERE laundryId = :id")
//    suspend fun getLaundryCategory(id: Int): List<LaundryCategoryEntity>

    @Update
    suspend fun updateLaundryCategoryCount(categoryEntity: LaundryCategoryEntity)
}