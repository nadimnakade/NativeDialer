package com.crm.v2.di

import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.data.network.api.AuthApi
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.interceptor.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .connectTimeout(ApiConstants.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(jsonMediaType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providePickupApi(retrofit: Retrofit): PickupApi {
        return retrofit.create(PickupApi::class.java)
    }
}
