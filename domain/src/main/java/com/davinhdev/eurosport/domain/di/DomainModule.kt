package com.davinhdev.eurosport.domain.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module
@ComponentScan("com.davinhdev.eurosport.domain.interactor")
class InteractorModule

val koinDomainModules = listOf(
    InteractorModule().module
)
