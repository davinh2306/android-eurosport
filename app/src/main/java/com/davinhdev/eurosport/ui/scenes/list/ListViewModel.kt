package com.davinhdev.eurosport.ui.scenes.list

import com.davinhdev.eurosport.domain.interactor.GetData
import com.davinhdev.eurosport.domain.model.Item
import com.davinhdev.eurosport.ui.scenes.base.AppDispatchers
import com.davinhdev.eurosport.ui.scenes.base.BaseAction
import com.davinhdev.eurosport.ui.scenes.base.BaseState
import com.davinhdev.eurosport.ui.scenes.base.BaseViewModel
import com.davinhdev.eurosport.ui.scenes.base.UiState
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
class ListViewModel(
    private val getData: GetData,
    appDispatchers: AppDispatchers
) : BaseViewModel<ListViewModel.Action, UiState<ListViewModel.State>>(appDispatchers, UiState.Loading) {

    override suspend fun onHandle(action: Action) {
        when (action) {
            is Action.LoadData -> loadData()
        }
    }

    init {
        handle(Action.LoadData)
    }

    private suspend fun loadData() {
        try {
            val result = getData()

            if (result.isNotEmpty()) {
                updateState {
                    UiState.Success(
                        State(
                            items = result
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Timber.e(e)

            updateState {
                UiState.Error(e)
            }
        }
    }

    sealed class Action : BaseAction {
        object LoadData : Action()
    }

    data class State(
        val items: List<Item> = listOf(),
    ) : BaseState
}
