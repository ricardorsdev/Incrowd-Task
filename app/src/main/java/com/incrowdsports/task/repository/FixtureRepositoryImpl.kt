package com.incrowdsports.task.repository

import com.incrowdsports.task.data.FixtureService
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.ServiceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(private val dataSource: FixtureService) :
    FixtureRepository {
    override fun getFixtureList(
        compId: Int, season: Int, size: Int, pageNumber: Int
    ): Flow<ServiceResult<List<Fixture>>> {
        return flow {
            emit(ServiceResult.Loading)
            val response = dataSource.getFixtureList(compId, season, size, pageNumber).data
            emit(ServiceResult.Success(response))
        }.catch {
            emit(ServiceResult.Failure(it.message))
        }
    }

    override fun getFixtureDetails(
        id: Long
    ): Flow<ServiceResult<Fixture>> {
        return flow {
            emit(ServiceResult.Loading)
            val response = dataSource.getFixtureDetails(id).data
            emit(ServiceResult.Success(response))
        }.catch {
            emit(ServiceResult.Failure(it.message))
        }
    }
}