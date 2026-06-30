package com.crm.v2.data.network.interceptor

import com.crm.v2.data.datastore.AuthDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = authDataStore.accessToken
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        val request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}
