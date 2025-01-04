package com.service.servicejunction.home.laundry.data.repository

import com.service.servicejunction.home.data.HomeDatabase
import com.service.servicejunction.home.laundry.data.local.LaundryCategoryEntity
import com.service.servicejunction.home.laundry.data.local.LaundryEntity
import com.service.servicejunction.home.laundry.data.mapper.toLaundryWithCategory
import com.service.servicejunction.home.laundry.domain.local.LaundryCategory
import com.service.servicejunction.home.laundry.domain.local.LaundryItem
import com.service.servicejunction.home.laundry.domain.local.LaundryWithCategory
import com.service.servicejunction.home.laundry.domain.repository.LaundryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaundryRepositoryImplementation @Inject constructor(
    db: HomeDatabase
): LaundryRepository {

    private val laundryDao = db.laundryDao

    override suspend fun getLaundryWithCategory(laundryList: List<LaundryItem>): List<LaundryWithCategory> {
        var laundryListWithCategory = laundryDao.getLaundryWithCategory()
        if(laundryListWithCategory.isEmpty()){
            laundryList.forEach { laundry ->
                val laundryId = laundryDao.insertLaundryTypes(LaundryEntity(laundryName = laundry.laundryType.first, iconRes = laundry.laundryType.second)).toInt()
                val categoryEntities = laundry.typesCategory.map { category ->
                    LaundryCategoryEntity(
                        laundryId = laundryId,
                        categoryName = category.first,
                        count = 0,
                        iconRes = category.second
                    )
                }
                laundryDao.insertLaundryCategory(categoryEntities)
            }
            laundryListWithCategory = laundryDao.getLaundryWithCategory()
        }
        return laundryListWithCategory.map {
            it.toLaundryWithCategory()
        }
    }

    override suspend fun updateLaundryCategory(categoryId: Int, laundryId: Int, categoryName: String, newCount: Int, iconRes: Int) {
        val updateCategory = LaundryCategoryEntity(
            categoryId = categoryId, laundryId = laundryId, categoryName = categoryName, count = newCount, iconRes = iconRes
        )
        laundryDao.updateLaundryCategoryCount(updateCategory)
    }

//    override suspend fun getLaundryCategory(laundryId: Int): List<LaundryCategory> {
//        val laundryCategoryEntity = laundryDao.getLaundryCategory(laundryId)
//        return laundryCategoryEntity.map {
//            it.toLaundryCategory()
//        }
//    }

}