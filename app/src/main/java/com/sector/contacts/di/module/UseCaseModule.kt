package com.sector.contacts.di.module

import com.sector.contacts.model.database.repository.UserRepository
import com.sector.contacts.model.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase =
        UserUseCase(userRepository = userRepository)

}