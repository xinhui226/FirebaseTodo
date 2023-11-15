package com.xinhui.mob201.ui.register

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.xinhui.mob201.core.service.AuthService
import com.xinhui.mob201.data.model.User
import com.xinhui.mob201.data.repo.UserRepo
import com.xinhui.mob201.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService,
    private val userRepo: UserRepo
) : BaseViewModel() {
    fun register(email: String, pass: String, confirmPass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val error = validate(email, pass, confirmPass)
            if(error.isNotEmpty()) {
                _error.emit(error)
            } else {
                val res = safeApiCall { authService.register(email, pass) }
                if (res == null) {
                    _error.emit("Could not create user")
                } else {
                     userRepo.addUser(
                         res.uid,
                         User(name = "khayrul Islam", email = res.email ?: "")
                     )
                    _success.emit("User created successfully")
                }
            }
        }
    }

    fun validate(email: String, pass: String, confirmPass: String): String {
        return if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Please provide a valid email address"
        } else if(pass.length < 6) {
            "Password length must be greater than 5"
        } else if(pass != confirmPass) {
            "Password and confirm password are not the same"
        } else {
            ""
        }
    }
}