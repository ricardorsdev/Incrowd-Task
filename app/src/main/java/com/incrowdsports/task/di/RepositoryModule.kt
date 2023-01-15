package com.incrowdsports.task.di

import com.incrowdsports.task.repository.FixtureRepository
import com.incrowdsports.task.repository.FixtureRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFixtureRepository(fixtureRepositoryImpl: FixtureRepositoryImpl): FixtureRepository
}