package com.incrowdsports.task.repository

import com.incrowdsports.task.data.FixtureService
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.http.Query
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(private val dataSource: FixtureService) :
    FixtureRepository {
    override fun getFixtureList(
        compId: Int,
        season: Int,
        size: Int,
    ): Flow<NetworkResponse<List<Fixture>>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            val response = dataSource.getFixtureList(compId, season, size)
            emit(NetworkResponse.Success(response))
        }.catch {
            emit(NetworkResponse.Failure(it.message))
        }
    }
}