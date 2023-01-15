package com.incrowdsports.task.repository

import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.ServiceResult
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {
    fun getFixtureList(
        compId: Int, season: Int, size: Int, pageNumber: Int
    ): Flow<ServiceResult<List<Fixture>>>

    fun getFixtureDetails(
        id: Long
    ): Flow<ServiceResult<Fixture>>
}