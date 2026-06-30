package com.crm.v2.domain.usecase.auth

import com.crm.v2.common.result.Resource
import com.crm.v2.domain.model.User
import com.crm.v2.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userName: String, password: String, uuid: String): Resource<User> {
        if (userName.isBlank()) return Resource.Error("Username is required")
        if (password.isBlank()) return Resource.Error("Password is required")
        return authRepository.login(userName, password, uuid)
    }
}

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.logout()
    }
}

class CheckSessionUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        val user = authRepository.getSavedUser()
        return user != null && user.token.isNotEmpty()
    }
}
