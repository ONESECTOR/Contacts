package com.sector.contacts.di.module

import androidx.room.Room
import com.sector.contacts.App
import com.sector.contacts.di.provider.DatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provide(app: App): DatabaseProvider {
        return Room
            .databaseBuilder(
                app,
                DatabaseProvider::class.java,
                DatabaseProvider.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}