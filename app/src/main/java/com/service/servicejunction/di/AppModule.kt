package com.service.servicejunction.di

import android.app.Application
import androidx.room.Room
import com.service.servicejunction.home.data.HomeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCompanyDatabase(application: Application): HomeDatabase {
        return Room.databaseBuilder(
            application,
            HomeDatabase::class.java,
            "HomeDB.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}