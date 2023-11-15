package com.xinhui.mob201.ui.addEditTodo.viewModel

import com.xinhui.mob201.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseAddEditTodoViewModel: BaseViewModel() {
    protected val _finish: MutableSharedFlow<Unit> = MutableSharedFlow()
    val finish: SharedFlow<Unit> = _finish
    abstract fun submit(title: String, desc: String)
}
