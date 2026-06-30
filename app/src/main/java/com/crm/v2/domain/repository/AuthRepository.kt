package com.crm.v2.domain.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(userName: String, password: String, uuid: String): Resource<User>
    suspend fun loginMobile(mobileNo: String, udid: String): Resource<User>
    suspend fun checkUserStatus(userKey: String): Resource<Boolean>
    suspend fun getSavedUser(): User?
    fun isLoggedIn(): Flow<Boolean>
    suspend fun logout()
    suspend fun savePin(pin: String)
    suspend fun verifyPin(pin: String): Boolean
    suspend fun isPinSet(): Boolean
    suspend fun saveFcmToken(token: String)
}
