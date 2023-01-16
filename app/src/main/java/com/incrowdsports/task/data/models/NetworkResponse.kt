package com.incrowdsports.task.data.models

data class NetworkResponse<T>(
    val data: T,
)

sealed class ServiceResult<out T> {
    object Loading: ServiceResult<Nothing>()
    data class Success<T>(val data: T): ServiceResult<T>()
    data class Failure(val message: String?): ServiceResult<Nothing>()
}