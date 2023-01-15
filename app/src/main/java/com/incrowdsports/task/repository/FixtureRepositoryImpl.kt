package com.incrowdsports.task.repository

import com.incrowdsports.task.data.FixtureService
import com.incrowdsports.task.data.models.Fixture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(private val dataSource: FixtureService) :
    FixtureRepository {
    override fun getFixtureList(): Flow<List<Fixture>> {
        TODO("Not yet implemented")
    }

}