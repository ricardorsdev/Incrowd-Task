package com.incrowdsports.task.repository

import com.incrowdsports.task.data.models.Fixture
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {
    fun getFixtureList(): Flow<List<Fixture>>
}