package com.davinhdev.eurosport.domain.interactor

abstract class NoParamsInteractor<R> {
    suspend operator fun invoke(): R = doWork()
    protected abstract suspend fun doWork(): R
}