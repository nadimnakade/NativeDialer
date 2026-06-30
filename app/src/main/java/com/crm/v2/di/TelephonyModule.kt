package com.crm.v2.di

import com.crm.v2.telephony.TelephonyManager
import com.crm.v2.telephony.provider.CloudConnectProvider
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
    abstract fun bindTelephonyManager(impl: CloudConnectProvider): TelephonyManager
}

// To switch to PJSIP, uncomment below and comment out the CloudConnect binding above:
//
// import com.crm.v2.telephony.pjsip.PjsipManager
// import com.crm.v2.telephony.pjsip.PjsipTelephonyProvider
// import dagger.Provides
//
// @Module
// @InstallIn(SingletonComponent::class)
// object TelephonyModule {
//
//     @Provides
//     @Singleton
//     fun providePjsipManager(): PjsipManager = PjsipManager(context)
//
//     @Binds
//     @Singleton
//     abstract fun bindTelephonyManager(impl: PjsipTelephonyProvider): TelephonyManager
// }
