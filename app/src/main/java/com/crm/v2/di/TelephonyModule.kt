package com.crm.v2.di

import com.crm.v2.telephony.TelephonyManager
import com.crm.v2.telephony.pjsip.PjsipTelephonyProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TelephonyModule {

    @Binds
    @Singleton
    abstract fun bindTelephonyManager(impl: PjsipTelephonyProvider): TelephonyManager
}
