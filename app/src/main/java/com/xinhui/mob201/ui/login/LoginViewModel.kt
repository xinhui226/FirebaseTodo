package com.xinhui.mob201.ui.login

import androidx.lifecycle.viewModelScope
import com.xinhui.mob201.core.service.AuthService
import com.xinhui.mob201.data.repo.TodosRepo
import com.xinhui.mob201.data.repo.UserRepo
import com.xinhui.mob201.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val userRepo: UserRepo,
    private val todosRepo: TodosRepo
): BaseViewModel() {
    private val _greet = MutableStateFlow("")
    val greet:StateFlow<String> = _greet

    init {
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall { todosRepo.greet() }?.let {
                _greet.value = it
            }
        }
    }
    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = safeApiCall { authService.login(email, pass) }

            if(res == null) {
                _error.emit("Email or password is wrong")
            } else {
                _success.emit("Login successful")
            }
        }
    }
}