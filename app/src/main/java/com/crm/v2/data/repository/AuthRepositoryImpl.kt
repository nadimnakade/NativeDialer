package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.data.network.api.AuthApi
import com.crm.v2.domain.model.User
import com.crm.v2.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDataStore: AuthDataStore,
) : AuthRepository {

    override suspend fun login(userName: String, password: String, uuid: String): Resource<User> {
        return try {
            val response = authApi.login(userName, password, uuid)
            if (response.isSuccessful) {
                val list = response.body()
                val body = list?.firstOrNull()
                body?.let {
                    val key = it.userKey.toString()
                    val user = User(
                        key = key,
                        employeeName = it.employeeName,
                        userName = it.userName,
                        designation = it.designation ?: "",
                        role = it.role ?: "",
                        token = it.token,
                        tokenExpiry = parseExpiry(it.expiration)
                    )
                    authDataStore.accessToken = it.token
                    authDataStore.userKey = key
                    authDataStore.employeeName = it.employeeName
                    authDataStore.userName = it.userName
                    authDataStore.designationDesc = it.designation
                    authDataStore.roleDesc = it.role
                    authDataStore.photoUrl = it.photoUrl
                    authDataStore.isLoggedIn = true
                    authDataStore.tokenExpiry = parseExpiry(it.expiration)
                    Resource.Success(user)
                } ?: Resource.Error("Empty response from server")
            } else {
                val errorBody = response.errorBody()?.string()
                val message = parseServerError(errorBody) ?: "Invalid username or password"
                Resource.Error(message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error during login", e)
        }
    }

    override suspend fun loginMobile(mobileNo: String, udid: String): Resource<User> {
        return try {
            val response = authApi.loginMobile(mobileNo, udid)
            if (response.isSuccessful) {
                val list = response.body()
                val body = list?.firstOrNull()
                body?.let {
                    val key = it.userKey.toString()
                    val user = User(
                        key = key,
                        employeeName = it.employeeName,
                        userName = it.userName,
                        designation = it.designation ?: "",
                        role = it.role ?: "",
                        token = it.token,
                        tokenExpiry = parseExpiry(it.expiration)
                    )
                    authDataStore.accessToken = it.token
                    authDataStore.userKey = key
                    authDataStore.employeeName = it.employeeName
                    authDataStore.userName = it.userName
                    authDataStore.designationDesc = it.designation
                    authDataStore.roleDesc = it.role
                    authDataStore.photoUrl = it.photoUrl
                    authDataStore.isLoggedIn = true
                    authDataStore.tokenExpiry = parseExpiry(it.expiration)
                    Resource.Success(user)
                } ?: Resource.Error("Empty response from server")
            } else {
                val errorBody = response.errorBody()?.string()
                val message = parseServerError(errorBody) ?: "Mobile login failed"
                Resource.Error(message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error during mobile login", e)
        }
    }

    override suspend fun checkUserStatus(userKey: String): Resource<Boolean> {
        return try {
            val response = authApi.getUserStatus(userKey)
            if (response.isSuccessful) {
                Resource.Success(response.body()?.isActive ?: true)
            } else {
                Resource.Success(true)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to check user status", e)
        }
    }

    override suspend fun getSavedUser(): User? {
        val token = authDataStore.accessToken ?: return null
        val userKey = authDataStore.userKey ?: return null
        return User(
            key = userKey,
            employeeName = authDataStore.employeeName ?: "",
            userName = authDataStore.userName ?: "",
            designation = authDataStore.designationDesc ?: "",
            role = authDataStore.roleDesc ?: "",
            token = token,
            tokenExpiry = authDataStore.tokenExpiry
        )
    }

    override fun isLoggedIn(): Flow<Boolean> = flow {
        emit(authDataStore.isLoggedIn)
    }

    override suspend fun logout() {
        authDataStore.clear()
    }

    override suspend fun savePin(pin: String) {
        val hash = MessageDigest.getInstance("SHA-256")
            .digest(pin.toByteArray())
            .joinToString("") { "%02x".format(it) }
        authDataStore.pinHash = hash
    }

    override suspend fun verifyPin(pin: String): Boolean {
        val storedHash = authDataStore.pinHash ?: return false
        val inputHash = MessageDigest.getInstance("SHA-256")
            .digest(pin.toByteArray())
            .joinToString("") { "%02x".format(it) }
        return storedHash == inputHash
    }

    override suspend fun isPinSet(): Boolean {
        return authDataStore.pinHash != null
    }

    override suspend fun saveFcmToken(token: String) {
        authDataStore.fcmToken = token
    }

    companion object {
        private fun parseServerError(errorBody: String?): String? {
            if (errorBody.isNullOrBlank()) return null
            return try {
                val json = JSONObject(errorBody)
                json.optString("message")
                    .ifBlank { json.optString("error") }
                    .ifBlank { json.optString("detail") }
                    .ifBlank { null }
            } catch (_: Exception) {
                null
            }
        }

        private fun parseExpiry(expiration: String): Long {
            return try {
                java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US)
                    .parse(expiration)?.time ?: (System.currentTimeMillis() + 480 * 60 * 1000)
            } catch (_: Exception) {
                System.currentTimeMillis() + 480 * 60 * 1000
            }
        }
    }
}
