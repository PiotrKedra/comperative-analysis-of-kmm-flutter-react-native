package com.example.kmm_network.interactors

import com.example.kmm_network.datasource.cache.UserCache
import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.domain.DataState
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.interactors.util.CommonFlow
import com.example.kmm_network.interactors.util.asCommonFlow
import kotlinx.coroutines.flow.flow

class CreateUser(
    private val userService: UserService,
    private val userCache: UserCache
) {

    fun execute(user: User) : CommonFlow<DataState<User>> = flow {

        emit(DataState.loading())

        try {
            val createdUser = userService.create(user)
            userCache.insert(user)
            emit(DataState.success(data = createdUser))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }.asCommonFlow()
}