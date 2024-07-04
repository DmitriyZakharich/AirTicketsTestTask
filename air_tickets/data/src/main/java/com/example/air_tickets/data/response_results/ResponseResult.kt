package com.example.air_tickets.data.response_results

sealed interface ResponseResult {
    data class Success<T>(val data: T): ResponseResult
    data object Failure: ResponseResult
}