package com.davinhdev.eurosport.data.di

import com.davinhdev.eurosport.data.repository.NetworkEurosportRepository
import com.davinhdev.eurosport.domain.repository.EurosportRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ksp.generated.module

val repositoryModule = module {
    singleOf(::NetworkEurosportRepository) { bind<EurosportRepository>() }
}

@Module
@ComponentScan("com.davinhdev.eurosport.data.network")
class NetworkModule

val koinDataModules = listOf(
    repositoryModule,
    NetworkModule().module,
)