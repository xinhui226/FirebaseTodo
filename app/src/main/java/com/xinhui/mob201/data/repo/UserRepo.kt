package com.xinhui.mob201.data.repo

import com.xinhui.mob201.data.model.User

interface UserRepo {
    suspend fun addUser(id: String, user: User)
    suspend fun getUser(id: String): User?
    suspend fun updateUser(id: String, user: User)
}