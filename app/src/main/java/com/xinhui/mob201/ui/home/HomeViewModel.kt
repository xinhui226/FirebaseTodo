package com.xinhui.mob201.ui.home

import androidx.lifecycle.viewModelScope
import com.xinhui.mob201.data.model.Todo
import com.xinhui.mob201.data.repo.TodosRepo
import com.xinhui.mob201.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TodosRepo
) : BaseViewModel() {
    private val _todos: MutableStateFlow<List<Todo>> = MutableStateFlow(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        getAllTodos()
    }

    fun getAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall { repo.getAllTodos() }?.collect {
                _todos.value = it
            }
        }
    }

    fun refresh() {
        getAllTodos()
    }

    fun delete(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(todo.id)
        }
    }
}