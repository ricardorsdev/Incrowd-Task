package com.incrowdsports.task.repository

import com.incrowdsports.task.data.models.Fixture
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {
    fun getFixtureList(
        compId: Int,
        season: Int,
        size: Int,
    ): Flow<List<Fixture>>
}