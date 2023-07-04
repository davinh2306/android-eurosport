package com.davinhdev.eurosport.ui.scenes.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<A : BaseAction, S : BaseState>(
    private val dispatchers: AppDispatchers,
    val initialState: S
) : ViewModel(),
    CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext =
        dispatchers.io + job + CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            Timber.e(exception)
        }

    private val internalState = MutableStateFlow(initialState)

    val state = internalState.asStateFlow()

    protected abstract suspend fun onHandle(action: A)

    fun handle(action: A) = launch {
        onHandle(action)
    }

    override fun onCleared() {
        job.cancelChildren()
    }

    protected suspend fun updateState(reducer: (S) -> S) = withContext(dispatchers.main) {
        internalState.value = reducer(state.value)
    }
}

sealed class UiState<out T> : BaseState {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val e: Exception) : UiState<Nothing>()
}

interface BaseAction
interface BaseState

@Composable
fun <A : BaseAction, S : BaseState> BaseViewModel<A, S>.watchAsState(): State<S> {
    return rememberFlowWithLifecycle(state).collectAsState(initialState)
}