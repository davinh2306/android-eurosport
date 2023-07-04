package com.davinhdev.eurosport.domain.repository

import com.davinhdev.eurosport.domain.model.Response

interface EurosportRepository {
    suspend fun getItems(): Result<Response>
}
