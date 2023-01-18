package com.sector.contacts.di.module

import com.sector.contacts.di.provider.DatabaseProvider
import com.sector.contacts.model.database.repository.UserRepository
import com.sector.contacts.model.database.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(databaseProvider: DatabaseProvider): UserRepository =
        UserRepositoryImpl(databaseProvider = databaseProvider)

}