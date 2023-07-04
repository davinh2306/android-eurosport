package com.davinhdev.eurosport.data.repository

import com.davinhdev.eurosport.data.BuildConfig
import com.davinhdev.eurosport.data.mapper.toResponse
import com.davinhdev.eurosport.data.model.NetworkResponse
import com.davinhdev.eurosport.data.network.HttpClientManager
import com.davinhdev.eurosport.data.network.HttpClientManager.Companion.PATH
import com.davinhdev.eurosport.domain.model.Response
import com.davinhdev.eurosport.domain.repository.EurosportRepository
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class NetworkEurosportRepository(
    private val httpClientManager: HttpClientManager,
) : EurosportRepository {
    override suspend fun getItems(): Result<Response> {
        return httpClientManager.requestCatching {
            var responseBody: NetworkResponse?

            try {
                responseBody = it.get("${BuildConfig.EUROSPORT_API_DOMAIN}$PATH").body<NetworkResponse>()
            } catch (e: NoTransformationFoundException) {
                val responseBodyString: String = it.get("${BuildConfig.EUROSPORT_API_DOMAIN}$PATH").body()
                val json = Json {
                    ignoreUnknownKeys = true
                }
                responseBody = json.decodeFromString(responseBodyString)
            }

            responseBody?.toResponse() ?: Response(listOf(), listOf())
        }
    }
}
