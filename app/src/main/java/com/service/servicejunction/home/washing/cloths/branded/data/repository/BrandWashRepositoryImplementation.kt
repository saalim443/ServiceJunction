package com.service.servicejunction.home.washing.cloths.branded.data.repository

import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandDetailsEntity
import com.service.servicejunction.home.washing.cloths.branded.data.local.BrandEntity
import com.service.servicejunction.home.data.HomeDatabase
import com.service.servicejunction.home.washing.cloths.branded.data.mapper.toBrandWithDetails
import com.service.servicejunction.home.washing.cloths.branded.domain.local.BrandWithDetails
import com.service.servicejunction.home.washing.cloths.branded.domain.local.Item
import com.service.servicejunction.home.washing.cloths.branded.domain.repository.BrandWashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrandWashRepositoryImplementation @Inject constructor(
    db: HomeDatabase
): BrandWashRepository {
    private val brandDao = db.washingDao

    override suspend fun getCompanyWithDetails(brandList: List<String>, brandDetails: List<Item>): List<BrandWithDetails> {
        var brandListWithDetails = brandDao.getBrandWithDetails()
        if(brandListWithDetails.isEmpty()){
            brandList.forEach { companyName ->
                val brandId = brandDao.insertBrandList(BrandEntity(bandName = companyName)).toInt()
                val detailList = brandDetails.map { clothType ->
                    BrandDetailsEntity(brandId = brandId, item = clothType.name, count = 0,
                        price = clothType.price, iconRes = clothType.iconRes)
                }
                brandDao.insertDetails(detailList)

                brandListWithDetails = brandDao.getBrandWithDetails()
            }
        }
        return brandListWithDetails.map {
            it.toBrandWithDetails()
        }
    }

    override suspend fun updateDetailCount(
        detailsId: Int, newCount: Int, companyId: Int, text: String, price: Double, iconRes: Int
    ) {
        val updateDetail = BrandDetailsEntity(
            detailsId, count = newCount, brandId = companyId
            , item = text, price = price, iconRes = iconRes
        )
        brandDao.updateCompanyDetailCount(updateDetail)
    }

}