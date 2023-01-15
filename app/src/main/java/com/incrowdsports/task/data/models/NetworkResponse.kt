package com.incrowdsports.task.data.models

data class NetworkResponse<T>(
    val data: T,
)

sealed class ServiceResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : ServiceResult<T>()
    class Success<T>(data: T) : ServiceResult<T>(data = data)
    class Failure<T>(errorMessage: String?) : ServiceResult<T>(message = errorMessage)
}