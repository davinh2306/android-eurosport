package com.davinhdev.eurosport.ui.di

import com.davinhdev.eurosport.ui.navigation.DateAdapter
import com.davinhdev.eurosport.ui.scenes.base.AppDispatchers
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

val appModule = module {
    single { AppDispatchers() }

    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .addAdapter(DateAdapter())
            .build()
    }
}

@Module
@ComponentScan("com.davinhdev.eurosport.ui")
class ViewModelModule

val koinAppModules = listOf(
    appModule,
    ViewModelModule().module
)