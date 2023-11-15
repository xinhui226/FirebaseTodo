package com.xinhui.mob201.ui.addEditTodo

import androidx.fragment.app.viewModels
import com.xinhui.mob201.ui.addEditTodo.viewModel.AddTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment: BaseAddEditTodoFragment() {
    override val viewModel: AddTodoViewModel by viewModels()
}