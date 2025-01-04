package com.service.servicejunction.home.washing.cloths.branded.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface WashingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrandList(brandEntity: BrandEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: List<BrandDetailsEntity>)

    @Transaction
    @Query("SELECT * FROM brandentity")
    suspend fun getBrandWithDetails(): List<BrandWithDetailsRelation>

    @Update
    suspend fun updateCompanyDetailCount(detail: BrandDetailsEntity)
}