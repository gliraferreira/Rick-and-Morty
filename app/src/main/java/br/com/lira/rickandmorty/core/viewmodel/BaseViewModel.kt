package br.com.lira.rickandmorty.core.viewmodel

import androidx.lifecycle.ViewModel

interface ViewState

interface ViewAction

abstract class BaseViewModel<out State : ViewState, out Action : ViewAction> : ViewModel() {
    abstract val state: State
    abstract val action: Action
}