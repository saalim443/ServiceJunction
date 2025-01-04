package com.service.servicejunction.di

import com.service.servicejunction.home.laundry.data.repository.LaundryRepositoryImplementation
import com.service.servicejunction.home.laundry.domain.repository.LaundryRepository
import com.service.servicejunction.home.washing.cloths.branded.data.repository.BrandWashRepositoryImplementation
import com.service.servicejunction.home.washing.cloths.branded.domain.repository.BrandWashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBrandWashRepository(
        brandWashRepositoryImplementation: BrandWashRepositoryImplementation
    ): BrandWashRepository

    @Binds
    @Singleton
    abstract fun bindLaundryRepository(
        laundryRepositoryImplementation: LaundryRepositoryImplementation
    ): LaundryRepository
}