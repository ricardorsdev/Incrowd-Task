package com.incrowdsports.task.repository

import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {
    fun getFixtureList(
        compId: Int,
        season: Int,
        size: Int,
    ): Flow<NetworkResponse<List<Fixture>>>
}