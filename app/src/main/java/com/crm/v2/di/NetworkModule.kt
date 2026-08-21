package com.crm.v2.di

import android.util.Log
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.data.network.api.AuthApi
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.api.SearchApplicationApi
import com.crm.v2.common.network.NetworkMonitor
import com.crm.v2.common.network.NetworkMonitorImpl
import com.crm.v2.data.network.interceptor.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TAG = "API_LOG"
    private val jsonMediaType = "application/json".toMediaType()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val responseLogger = okhttp3.Interceptor { chain ->
            val request = chain.request()
            val ts = java.text.SimpleDateFormat("HH:mm:ss.SSS", java.util.Locale.getDefault()).format(java.util.Date())
            Log.d(TAG, "[$ts] >>> ${request.method} ${request.url}")

            val response = chain.proceed(request)
            val body = response.body?.string()

            Log.d(TAG, "[$ts] <<< ${response.code} ${request.url}")
            if (body != null) {
                val chunked = body.chunked(3000)
                chunked.forEachIndexed { i, part ->
                    Log.d(TAG, "[$ts] <<< BODY[$i]: $part")
                }
            }

            val contentType = response.body?.contentType()
            val newBody = if (body != null) okhttp3.ResponseBody.create(contentType, body) else null
            response.newBuilder().body(newBody).build()
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(responseLogger)
            .addInterceptor(logging)
            .connectTimeout(ApiConstants.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    @Named("main")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(jsonMediaType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(@Named("main") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providePickupApi(@Named("main") retrofit: Retrofit): PickupApi {
        return retrofit.create(PickupApi::class.java)
    }

    @Provides
    @Singleton
    @Named("search")
    fun provideSearchApplicationRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://1up.co.in/dialerapi/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchApplicationApi(@Named("search") retrofit: Retrofit): SearchApplicationApi {
        return retrofit.create(SearchApplicationApi::class.java)
    }

    @Binds
    @Singleton
    fun bindNetworkMonitor(impl: NetworkMonitorImpl): NetworkMonitor
}
