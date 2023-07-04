package com.davinhdev.eurosport.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import org.koin.core.annotation.Single

@Single
class HttpClientManager {
    companion object {
        const val PATH = "/api/json-storage/bin/edfefba"
        private const val DEFAULT_TIME_OUT_MS = 60000L
    }

    private val client = HttpClient(OkHttp) {
        install(HttpTimeout) { requestTimeoutMillis = DEFAULT_TIME_OUT_MS }
    }

    suspend fun <T> requestCatching(
        block: suspend (HttpClient) -> T,
    ): Result<T> = try {
        Result.success(block(client))
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}