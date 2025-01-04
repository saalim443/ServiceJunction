package com.service.servicejunction.home.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.service.servicejunction.home.laundry.data.local.LaundryCategoryEntity
import com.service.servicejunction.home.laundry.data.local.LaundryDao
import com.service.servicejunction.home.laundry.data.local.LaundryEntity
import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandDetailsEntity
import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandEntity
import com.service.servicejunction.home.washing.cloths.branded.data.local.WashingDao

@Database(entities = [BrandEntity::class, BrandDetailsEntity::class, LaundryEntity::class,
    LaundryCategoryEntity::class],
    version = 6, exportSchema = false)
abstract class HomeDatabase: RoomDatabase() {
    abstract val washingDao: WashingDao
    abstract val laundryDao: LaundryDao
}