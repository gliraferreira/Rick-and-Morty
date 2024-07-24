package br.lira.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ViewModel<State : ViewState, Action : ViewAction>(
    initialState: State
) : ViewModel() {

    private val _state = MutableLiveData<State>().apply { value = initialState }

    private val _action = SingleLiveData<Action>()

    val state: LiveData<State> get() = _state

    val action: LiveData<Action> get() = _action

    protected fun setState(block: (State) -> State) {
        _state.value?.let {
            _state.value = block(it)
        }
    }

    protected fun sendAction(block: () -> Action) {
        _action.value = block()
    }
}
