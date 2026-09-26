package com.crm.v2.di

import com.crm.v2.data.repository.*
import com.crm.v2.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
    @Binds @Singleton abstract fun bindLeadRepository(impl: LeadRepositoryImpl): LeadRepository
    @Binds @Singleton abstract fun bindReportingRepository(impl: ReportingRepositoryImpl): ReportingRepository
    @Binds @Singleton abstract fun bindDialerRepository(impl: DialerRepositoryImpl): DialerRepository
    @Binds @Singleton abstract fun bindCallLogRepository(impl: CallLogRepositoryImpl): CallLogRepository
    @Binds @Singleton abstract fun bindDailyLoginRepository(impl: DailyLoginRepositoryImpl): DailyLoginRepository
    @Binds @Singleton abstract fun bindDocumentRepository(impl: DocumentRepositoryImpl): DocumentRepository
    @Binds @Singleton abstract fun bindPickupRepository(impl: PickupRepositoryImpl): PickupRepository
    @Binds @Singleton abstract fun bindContactRepository(impl: ContactRepositoryImpl): ContactRepository
    @Binds @Singleton abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
    @Binds @Singleton abstract fun bindSearchApplicationRepository(impl: SearchApplicationRepositoryImpl): SearchApplicationRepository
}
