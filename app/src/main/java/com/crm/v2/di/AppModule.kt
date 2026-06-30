package com.crm.v2.di

import android.content.Context
import com.crm.v2.CrmApplication
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.data.datastore.SettingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCrmApplication(@ApplicationContext app: Context): CrmApplication {
        return app as CrmApplication
    }

    @Provides
    @Singleton
    fun provideAuthDataStore(@ApplicationContext context: Context): AuthDataStore {
        return AuthDataStore(context)
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDataStore {
        return SettingsDataStore(context)
    }
}

